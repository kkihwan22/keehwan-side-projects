package com.keehwan.infra.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.util.List;

public class StorageConfig {
    @Value("${cloud.aws.endpoint}")
    private String awsEndpoint;
    private static final String AWS_ACCESS_KEY = "foo";
    private static final String AWS_SECRET_KEY = "bar";


    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return AwsCredentialsProviderChain.builder()
                .reuseLastProviderEnabled(true)
                .credentialsProviders(List.of(
                        DefaultCredentialsProvider.create(),
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY))
                ))
                .build();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(awsCredentialsProvider())
                .region(Region.AP_NORTHEAST_2)
                .endpointOverride(URI.create(awsEndpoint))
                .build();
    }
}
