## aws-msk-cross-account-glue-schema-registry-sample

This repo contains a sample code for a Kafka Producer and Consumer written in Java showing how to access cross-account AWS Glue Schema Registry and use Avro Schema SpecificRecord to create and validate data records.

### Pre-requisites

You must have two AWS accounts:

* Account A – For the MSK cluster, Kafka producer and consumer Amazon Elastic Compute Cloud (Amazon EC2) instances, and AWS Cloud9 environment
* Account B – For the Schema Registry and schema
* Permissions to create AWS Resources in both the AWS accounts.

### To see the solution in action, use the following blog  

Refer the following blog to set up the cross account Glue Schema Registry and access it from Kafka Producer & Consumer from a different AWS account.

[Validate streaming data over Amazon MSK using schemas in cross-account AWS Glue Schema Registry](https://aws.amazon.com/blogs/big-data/validate-streaming-data-over-amazon-msk-using-schemas-in-cross-account-aws-glue-schema-registry/)

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.