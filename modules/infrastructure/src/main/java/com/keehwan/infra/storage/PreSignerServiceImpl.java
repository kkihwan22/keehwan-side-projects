package com.keehwan.infra.storage;

import com.keehwan.infra.consts.AwsProperties;
import com.keehwan.infra.consts.AwsProperties.S3;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Service
public class PreSignerServiceImpl implements PreSignerService {

    private final S3Presigner presigner;
    private final S3 s3Properties;

    public PreSignerServiceImpl(AwsProperties properties, AwsCredentialsProvider awsCredentialsProvider) {
        this.s3Properties = properties.getS3();
        this.presigner = S3Presigner.builder()
                .region(Region.of(properties.getRegion()))
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Override
    public URL generateUploadURL(String objectKey, Duration duration) {
        GetObjectPresignRequest request = GetObjectPresignRequest.builder()
                .signatureDuration(duration)
                .getObjectRequest(this.getObjectRequest(objectKey))
                .build();

        return presigner.presignGetObject(request).url();
    }

    private GetObjectRequest getObjectRequest(String objectKey) {
        return GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(objectKey)
                .build();
    }
}
