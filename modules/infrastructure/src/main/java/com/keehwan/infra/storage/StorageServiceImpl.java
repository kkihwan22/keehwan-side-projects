package com.keehwan.infra.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {

    private final S3Client client;

    @Override
    public void put(String bucket, String key, File file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        client.putObject(request, RequestBody.fromFile(file));
    }

    @Override
    public String getURL(String buket, String objectKey) {
        return "";
    }
}
