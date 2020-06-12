package com.jemhourmehdi.importbdx.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

    @Value("${amazon.key.access}")
    public String ACCESS;

    @Value("${amazon.key.secret}")
    public String SECRET;

    @Bean
    public AWSCredentialsProvider awsCredentials(){
        return new AWSStaticCredentialsProvider(getAwsCredentials());
    }

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3Client.builder()
                .standard()
                .withCredentials(awsCredentials())
                .withRegion(Regions.EU_WEST_3)
                .build();
    }

    @Bean
    public AmazonDynamoDB dynamoDB() {
        return AmazonDynamoDBClient.builder()
                .withCredentials(awsCredentials())
                .withRegion(Regions.EU_WEST_3)
                .build();
    }

    private AWSCredentials getAwsCredentials() {
        return new BasicAWSCredentials(ACCESS,SECRET);
    }
}
