package com.keehwan.infra.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;

public interface StorageService {

    void put(String buket, String objectKey, File file);
    String getURL(String buket, String objectKey);


}
