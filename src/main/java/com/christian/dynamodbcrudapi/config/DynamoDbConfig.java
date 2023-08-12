package com.christian.dynamodbcrudapi.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

    @Value("${dynamodb.access-key}")
    private  String ACCESS_KEY;
    @Value("${dynamodb.secret-key}")
    private  String SECRET_KEY;
    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return (AmazonDynamoDBAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "dynamodb.ca-central-1.amazonaws.com",
                                "ca-central-1"
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        ACCESS_KEY,
                                        SECRET_KEY
                                )
                        )
                )
                .build()
        );

    }

    @Bean
    public DynamoDBMapperConfig.TableNameOverride tableNameOverride(){
        return DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("user");
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverride){
        return DynamoDBMapperConfig.builder()
                .withTableNameOverride(tableNameOverride)
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig dynamoDBMapperConfig){
        return new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
    }
}
