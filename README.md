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

### Run Kafka Producer & Consumer
* To see Kafka Producer & Consumer in action, you need to follow the following AWS blog which has step-by-step instructions to create AWS resources in AWS Accounts and run Kafka Producer & Consumers.
* Blog title: __Validate Messages using AVRO SpecificRecord with Cross-Account Glue Schema Registry__

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.

