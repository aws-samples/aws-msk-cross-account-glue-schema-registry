## aws-msk-cross-account-glue-schema-registry

This repo contains a sample code for a Kafka Producer and Consumer written in Java showing how to access cross-account AWS Glue Schema Registry and use Avro Schema SpecificRecord to create and validate data records.

### Pre-requisites
You need
* 2 AWS Accounts.
* Permissions to create AWS Resources in both the AWS accounts.
* Local maven installation or an IDE with integrated maven to be able to run ```mvn compile``` for Kafka Producer and Consumer application.

### Kafka Producer
* Kafka Producer creates a Unicorn Ride Request data record and sends that to a Kafka topic. It uses ```unicorn_ride_request.avsc``` schema generated classes to create a data record.
* Kafka Producer uses an Avro SpecificRecord to ensure type-safety at a compile time.
* To start with, you need to generate the schema classes for Avro schema ```unicorn_ride_request.avsc``` that's available under the ```resources/avro-schema``` directory.
* Generate schema classes using the following or use your IDE’s integrated maven to run it

  mvn compile
* It will generate schema classes under ```src/main/java``` in the ```demo.glue.schema.registry.avro``` package.


### Kafka Consumer
* Kafka Consumer reads a Unicorn Ride Request data record from a Kafka topic. It also uses ```unicorn_ride_request.avsc``` schema generated classes to work with data records.
* Kafka Consumer also uses an Avro SpecificRecord to ensure type-safety at a compile time.
* To start with, you need to generate the schema classes for Avro schema ```unicorn_ride_request.avsc``` that's available under the ```resources/avro-schema``` directory.
* Generate schema classes using the following or use your IDE’s integrated maven to run it

  mvn compile
* It will generate schema classes under ```src/main/java``` in the ```demo.glue.schema.registry.avro``` package.

### CloudFormation templates
* In first AWS Account say Account A, let's create VPC, Kafka Producer and Consumer EC2 instances, and Amazon MSK cluster
* In second AWS Account say Account B, let's create a schema registry, and an Avro schema in the AWS Glue Schema Registry.
* To start with, login into Account B (Schema Registry Account) and use the following CloudFormation template to create Schema Registry, Avro Schema, and an IAM role that can be assumed by all the IAM principals of Account A.
  * [Schema Registry CloudFormation template](cfn-templates/cfn-schemaregistry.yaml)
* Once the schema registry stack is created, copy the Cross Account Kafka Producer and Consumer IAM role from the stack's Outputs tab. Copy __CrossAccountGlueSchemaRegistryRoleArn__ value.
* Let's move to Account A, login into Account A and use the following CloudFormation templates to create VPC, EC2 instances, Cloud9 environments, and Amazon MSK cluster
  * [Client template](cfn-templates/cfn-msk-clients.yaml)
  * [Amazon MSK cluster template](cfn-templates/cfn-msk-cluster.yaml)
* In Account A, follow these steps:
  * Go to EC2 console and take a note of KafkaProducerInstance and KafkaConsumerInstance private IP addresses.
  * Go to Cloud9 and open Cloud9EC2Bastion environment and upload EC2 keypair file that you used during the stack creation. Change key permissions

        chmod 0400 <keypair PEM file>
    
  * Open a new terminal and ssh into KafkaProducerInstance
    
        ssh -i <keypair PEM file> ec2-user@<KafkaProducerInstance Private IP address>
        
        aws configure set region ap-southeast-2 #change region as your requirement.

  * Use the following cli command to get the Amazon MSK cluster arn. if there more than one MSK cluster running in Account A, following command will return all the MSK clusters. Copy arn of the cluster that’s created by the MSKClusterStack stack.
  
        aws kafka list-clusters|grep ClusterArn|cut -d ':' -f 2-|cut -d ',' -f 1 | sed -e 's/\"//g'
  * export MSK_CLUSTER_ARN
    
        export MSK_CLUSTER_ARN=<MSK Cluster ARN, copy it from the previous step>
  * export BOOTSTRAP_SERVER
        
        export BOOTSTRAP_SERVER=$(aws kafka get-bootstrap-brokers \
        --cluster-arn $MSK_CLUSTER_ARN|grep BootstrapBrokerString|grep 9092| cut -d ':' -f 2- | sed -e \ 's/\"//g' | sed -e 's/,$//')
  * Verify environment variables
    
        echo $MSK_CLUSTER_ARN
        echo $BOOTSTRAP_SERVER

  * Create a Kafka topic called unicorn-ride-request-topic in your Amazon MSK cluster. This topic will be used by the Kafka Producer and Consumer applications later.
  
        cd ~/kafka

        ./bin/kafka-topics.sh --bootstrap-server $BOOTSTRAP_SERVER \
        --topic unicorn-ride-request-topic \
        --create --partitions 3 --replication-factor 2
        
        ./bin/kafka-topics.sh --bootstrap-server $BOOTSTRAP_SERVER --list
    
  * __MSKClientStack__ stack copied the Kafka producer client jar file called __kafka-cross-account-gsr-producer.jar__ to the __KafkaProducerInstance__ instance. It contains the Kafka producer client that sends messages to the Kafka topic __unicorn-ride-request-topic__ on the Amazon MSK cluster and accesses __unicorn-ride-request-schema-avro__ Avro schema from the __unicorn-ride-request-registry__ schema registry in __Account B__. 
  * Execute the following commands and verify __kafka-cross-account-gsr-producer.jar__ exists.
    
        cd ~
        ls -ls
  * Execute the following command to run the Kafka Producer in KafkaProducerInstance terminal.
  
        java -jar kafka-cross-account-gsr-producer.jar -bs $BOOTSTRAP_SERVER \
        -rn <Replace with CrossAccountGlueSchemaRegistryRoleArn value from SchemaRegistryStack stack Outputs tab in Account B> \
        -topic unicorn-ride-request-topic \
        -reg ap-southeast-2 \
        -nm 500

      * -bs: MSK cluster broker endpoints i.e., $MSK_BROKERS (environment variable pointing to MSK cluster brokers endpoint)
      * -rn: CrossAccountGlueSchemaRegistryRoleArn value from SchemaRegistryStack stack Outputs tab in Account B.
      * -topic: Kafka topic where you want producer to send the Unicorn Ride Request data records to.
      * -reg: Used for AWS STS region & AWS Glue Schema Registry region. Default is ap-southeast-2.
      * -nm: number of messages producer to send. Default is 100 messages.

  * Producer logs will showcase success retrieval of schema from the schema registry and delivering messages to the Kafka topic on MSK cluster.
  * Let's run the consumer
  * Open a new terminal in __Cloud9EC2Bastion__ Cloud9 environment.
  * ssh into KafkaConsumerInstance EC2 instance and set AWS region.
    
        ssh -i <keypair PEM file> ec2-user@<KafkaConsumerInstance Private IP address>
        aws configure set region ap-southeast-2 #change region as your requirement
  * Use the following cli command to get your Amazon MSK cluster arn. If there are more than one MSK cluster running in Account A, following command will return all the MSK clusters. Copy arn of the cluster that’s created by the MSKClusterStack stack.
  
        aws kafka list-clusters|grep ClusterArn|cut -d ':' -f 2-|cut -d ',' -f 1 | sed -e 's/\"//g'
      
  * Export MSK_CLUSTER_ARN
    
        export MSK_CLUSTER_ARN=<MSK Cluster ARN, copy it from the previous step>
  * Export BOOTSTRAP_SERVER

        export BOOTSTRAP_SERVER=$(aws kafka get-bootstrap-brokers \
        --cluster-arn $MSK_CLUSTER_ARN|grep BootstrapBrokerString|grep 9092| cut -d ':' -f 2- | sed -e \ 's/\"//g' | sed -e 's/,$//')
  * Verify environment variables
  
        echo $MSK_CLUSTER_ARN
        echo $BOOTSTRAP_SERVER
  * MSKClientStack stack copied the Kafka consumer client jar file called __kafka-cross-account-gsr-consumer.jar__ to the __KafkaConsumerInstance__ instance. It contains the Kafka consumer client that reads messages from the Kafka topic __unicorn-ride-request-topic__ on the Amazon MSK cluster and accesses __unicorn-ride-request-schema-avro__ Avro schema from the __unicorn-ride-request-registry__ schema registry in __Account B__. 
  * Execute the following commands and verify __kafka-cross-account-gsr-consumer.jar__ exists.
    
        cd ~
        ls -ls
  * Execute the following command to run the Kafka Consumer in KafkaConsumerInstance terminal.
    
        java -jar kafka-cross-account-gsr-consumer.jar -bs $BOOTSTRAP_SERVER \
        -rn <Replace with CrossAccountGlueSchemaRegistryRoleArn value from SchemaRegistryStack stack Outputs tab in Account B> \
        -topic unicorn-ride-request-topic \
        -reg ap-southeast-2

      * -bs: MSK cluster broker endpoints i.e., $MSK_BROKERS (environment variable pointing to MSK brokers endpoint)
      * -rn: CrossAccountGlueSchemaRegistryRoleArn value from SchemaRegistryStack stack Outputs tab in Account B.
      * -topic: Kafka topic where you want consumer to consume the Unicorn Ride Request data records from.
      * -reg: Used for AWS STS region. Default is ap-southeast-2

  * Kafka Consumer logs will show successfully reading messages from the Kafka topic on Amazon MSK Cluster in __Account A__ and accessing Avro schema __unicorn-ride-request-schema-avro__ from __unicorn-ride-request-registry__ schema registry in __Account B__.
* Both Kafka Producer and Consumer applications have been able to connect successfully with the centralised AWS Glue Schema Registry and are able to validate messages while sending and consuming messages. In the next section, let’s look at the Kafka Producer and Consumer implementations.

  


### Run Kafka Producer & Consumer
* To see Kafka Producer & Consumer in action, you need to follow the following AWS blog which has step-by-step instructions to create AWS resources in AWS Accounts and run Kafka Producer & Consumers.
* Blog title: __Validate Messages using AVRO SpecificRecord with Cross-Account Glue Schema Registry__

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.

