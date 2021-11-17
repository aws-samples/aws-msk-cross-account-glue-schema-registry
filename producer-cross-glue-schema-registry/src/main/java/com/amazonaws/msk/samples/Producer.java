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

import com.amazonaws.services.schemaregistry.serializers.GlueSchemaRegistryKafkaSerializer;
import com.amazonaws.services.schemaregistry.utils.AWSSchemaRegistryConstants;
import com.amazonaws.services.schemaregistry.utils.AvroRecordType;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import demo.glue.schema.registry.avro.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.model.DataFormat;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;
import software.amazon.awssdk.services.sts.model.StsException;

import java.util.Arrays;
import java.util.Properties;

public class Producer {

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
    @Parameter(names={"--num-messages", "-nm"},description="number of messages you want producer to send. Default is 100")
    private String str_numOfMessages = "100";
    @Parameter(names={"--iam-role-externalid", "-externalid"},description="ExternalId required for assuming IAM role in the schema registry account")
    private String externalId;

    private final static Logger logger = LoggerFactory.getLogger(org.apache.kafka.clients.producer.Producer.class.getName());
    public static void main(String[] args) {
        Producer producer = new Producer();
        JCommander jc = JCommander.newBuilder()
                .addObject(producer)
                .build();
        jc.parse(args);
        if (producer.help){
            jc.usage();
            return;
        }
        producer.startProducer();
    }
    /**
     * This method is used to start the Kafka producer.
     * It first uses the assumeGlueSchemaRegistryRole method
     * to assume an IAM role that is provided as a command
     * line argument. It then starts sending the Unicorn Ride Request
     * data records to the Kafka topic that is provided as a
     * command line argument, default is unicorn-ride-request-topic.
     * @return Nothing
     */
    public void startProducer() {
        assumeGlueSchemaRegistryRole();
        KafkaProducer<String, UnicornRideRequest> producer = new KafkaProducer<String,UnicornRideRequest>(getProducerConfig());
        int numberOfMessages = Integer.valueOf(str_numOfMessages);
        logger.info("Starting to send records...");
        for(int i = 0;i < numberOfMessages;i ++)
        {
            UnicornRideRequest rideRequest = getRecord(i);
            String key = "key-" + i;
            ProducerRecord<String, UnicornRideRequest> record = new ProducerRecord<String, UnicornRideRequest>(topic, key, rideRequest);
            producer.send(record, new ProducerCallback());
        }
    }
    /**
     * This method is creates a Unicorn Ride Request
     * object using Avro schema generated classes.
     * Values for Unicorn Ride Request object attributes
     * are hard-coded just for a demo purposes.
     * @return demo.glue.schema.registry.avro.UnicornRideRequest object
     */
    public UnicornRideRequest getRecord(int requestId){
            /*
             Initialise UnicornRideRequest object of
             class that is generated from AVRO Schema
             */
           UnicornRideRequest rideRequest = UnicornRideRequest.newBuilder()
            .setRequestId(requestId)
            .setPickupAddress("Melbourne, Victoria, Australia")
            .setDestinationAddress("Sydney, NSW, Aus")
            .setRideFare(1200.50F)
            .setRideDuration(120)
            .setPreferredUnicornColor(UnicornPreferredColor.WHITE)
            .setRecommendedUnicorn(RecommendedUnicorn.newBuilder()
                    .setUnicornId(requestId*2)
                    .setColor(unicorn_color.WHITE)
                    .setStarsRating(5).build())
            .setCustomer(Customer.newBuilder()
                    .setCustomerAccountNo(1001)
                    .setFirstName("Dummy")
                    .setLastName("User")
                    .setEmailAddresses(Arrays.asList("demo@example.com"))
                    .setCustomerAddress("Flinders Street Station")
                    .setModeOfPayment(ModeOfPayment.CARD)
                    .setCustomerRating(5).build()).build();
           logger.info(rideRequest.toString());
            return rideRequest;
    }
    /**
     * This method creates a Kafka producer configuration
     * properties object with relevant config values
     * and returns the configuration properties object to
     * the caller.
     * @return java.util.Properties Kafka properties configuration properties
     */
    private Properties getProducerConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"msk-cross-account-gsr-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GlueSchemaRegistryKafkaSerializer.class.getName());
        props.put(AWSSchemaRegistryConstants.DATA_FORMAT, DataFormat.AVRO.name());
        props.put(AWSSchemaRegistryConstants.AWS_REGION,regionName);
        props.put(AWSSchemaRegistryConstants.REGISTRY_NAME, "unicorn-ride-request-registry");
        props.put(AWSSchemaRegistryConstants.SCHEMA_NAME, "unicorn-ride-request-schema-avro");
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
                    .roleSessionName("kafka-producer-cross-account-glue-schemaregistry-demo")
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
    private class ProducerCallback implements Callback {
        /**
         * This method is a callback method which gets
         * invoked when Kafka producer receives the messages delivery
         * acknowledgement.
         * @return Nothing
         */
        @Override
        public void onCompletion(RecordMetadata recordMetaData, Exception e){
            if (e == null) {
                logger.info("Received new metadata. \n" +
                        "Topic:" + recordMetaData.topic() + "\n" +
                        "Partition: " + recordMetaData.partition() + "\n" +
                        "Offset: " + recordMetaData.offset() + "\n" +
                        "Timestamp: " + recordMetaData.timestamp());
            }
            else {
                logger.info("There's been an error from the Producer side");
                e.printStackTrace();
            }
        }
    }
}
