package com.keehwan.infra.consts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "cloud.aws")
@Component
@Getter
@Setter
public class AwsProperties {
    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String region;

    private S3 s3 = new S3();

    @Getter @Setter
    public static class S3 {
        private String bucket;
    }
}
