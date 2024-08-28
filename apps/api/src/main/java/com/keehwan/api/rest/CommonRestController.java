package com.keehwan.api.rest;

import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.share.BaseRestController;
import com.keehwan.infra.storage.PreSignerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
public class CommonRestController implements BaseRestController {

    private final PreSignerService preSignerService;

    @PostMapping("/api/v1/commons/presigned/upload")
    public ApiResponse<String> issuePresignedUploadURL() {
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyyMMdd/HHmmss"));
        URL url = preSignerService.generateUploadURL("profiles/" + format, Duration.ofSeconds(300));
        return ApiResponse.just(url.toString());
    }
}
