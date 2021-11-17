/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: MIT-0
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.amazonaws.msk.samples;

import com.amazonaws.services.schemaregistry.deserializers.GlueSchemaRegistryKafkaDeserializer;
import com.amazonaws.services.schemaregistry.utils.AWSSchemaRegistryConstants;
import com.amazonaws.services.schemaregistry.utils.AvroRecordType;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import demo.glue.schema.registry.avro.UnicornRideRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;
import software.amazon.awssdk.services.sts.model.StsException;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    @Parameter(names = {"--help", "-h"}, help = true)
    private boolean help = false;
    @Parameter(names={"--bootstrap-servers", "-bs"},description="kafka bootstrap servers endpoint")
    private String bootstrapServers;
    @Parameter(names={"--role-arn", "-rn"},description="ARN of a role in Glue Schema Registry AWS Account that producer will assume")
    private String assumeRoleARN;
    @Parameter(names={"--region", "-reg"},description="AWS Region where you want to point AWS STS client to e.g. us-east-1 Default is ap-southeast-2. Producer is only checking for the following regions ap-southeast-2, ap-southeast-1, us-east-1, us-east-2, us-west-1, us-west-2")
    private String regionName = "us-east-1";
    @Parameter(names={"--topic-name", "-topic"},description="Kafka topic name where you send the data records. Default is unicorn-ride-request-topic")
    private String topic = "unicorn-ride-request-topic";
    @Parameter(names={"--iam-role-externalid", "-externalid"},description="ExternalId required for assuming IAM role in the schema registry account")
    private String externalId;

    private final static Logger logger = LoggerFactory.getLogger(java.util.function.Consumer.class.getName());


    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        JCommander jc = JCommander.newBuilder()
                        .addObject(consumer)
                        .build();
        jc.parse(args);
        if (consumer.help){
            jc.usage();
            return;
        }
        consumer.startConsumer();

    }
    /**
     * This method is used to start the Kafka consumer.
     * It first uses the assumeGlueSchemaRegistryRole method
     * to assume an IAM role that is provided as a command
     * line argument. It then starts polling the Kafka topic
     * that is provided as a command line argument,
     * default is unicorn-ride-request-topic.
     * @return Nothing
     */
    public void startConsumer() {
        logger.info("starting consumer...");
        assumeGlueSchemaRegistryRole();
        KafkaConsumer<String, UnicornRideRequest> consumer = new KafkaConsumer<String, UnicornRideRequest>(getConsumerConfig());
        consumer.subscribe(Collections.singletonList(topic));
        int count = 0;
        while (true) {
            final ConsumerRecords<String, UnicornRideRequest> records = consumer.poll(Duration.ofMillis(1000));
            for (final ConsumerRecord<String, UnicornRideRequest> record : records) {
                final UnicornRideRequest rideRequest = record.value();
                logger.info(String.valueOf(rideRequest.getRequestId()));
                logger.info(rideRequest.toString());
            }
        }
    }
    /**
     * This method creates a Kafka consumer configuration
     * properties object with relevant config values
     * and returns the configuration properties object to
     * the caller.
     * @return java.util.Properties Kafka consumer configuration properties
     */
    private Properties getConsumerConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "unicorn.riderequest.consumer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GlueSchemaRegistryKafkaDeserializer.class.getName());
        props.put(AWSSchemaRegistryConstants.AWS_REGION, this.regionName);
        props.put(AWSSchemaRegistryConstants.AVRO_RECORD_TYPE, AvroRecordType.SPECIFIC_RECORD.getName());
        return props;
    }
    /**
     * This method uses AWS STS to assume the
     * IAM Role that is passed as a command line
     * argument. It sets aws.accessKeyId, aws.secretAccessKey
     * and aws.sessionToken in the system properties.
     * @return Nothing
     */
    public void assumeGlueSchemaRegistryRole() {
        try {
             Region region = Region.of(regionName);
             if(!Region.regions().contains(region))
                throw new RuntimeException("Region : " + regionName + " is invalid.");
            StsClient stsClient = StsClient.builder().region(region).build();
            AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                    .roleArn(this.assumeRoleARN)
                    .roleSessionName("kafka-consumer-cross-account-glue-schemaregistry-demo")
                    .externalId(this.externalId)
                    .build();
            AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
            Credentials myCreds = roleResponse.credentials();
            System.setProperty("aws.accessKeyId", myCreds.accessKeyId());
            System.setProperty("aws.secretAccessKey", myCreds.secretAccessKey());
            System.setProperty("aws.sessionToken", myCreds.sessionToken());
            stsClient.close();
        } catch (StsException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
    }
}

