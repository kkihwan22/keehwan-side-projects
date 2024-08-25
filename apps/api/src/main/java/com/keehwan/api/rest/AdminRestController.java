package com.keehwan.api.rest;

import com.keehwan.api.rest.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    @GetMapping("/admin/v1/users")
    public ApiResponse<Void> sampleUsers() {
        return ApiResponse.just(null);
    }
}
