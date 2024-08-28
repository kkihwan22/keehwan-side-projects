package com.keehwan.infra.storage;

import java.net.URL;
import java.time.Duration;

public interface PreSignerService {

    URL generateUploadURL(String objectKey, Duration duration);
}
