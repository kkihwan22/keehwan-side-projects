package com.keehwan.api.rest;

import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.rest.dto.UserDTO.UserCreateRequest;
import com.keehwan.api.rest.facade.UserApplication;
import com.keehwan.api.share.BaseRestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRestController implements BaseRestController {
    private final UserApplication userApplication;

    @PostMapping("/api/v1/users")
    public ApiResponse<String> createUser(@Valid @RequestBody UserCreateRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        userApplication.createUser(request);
        return ApiResponse.just("ok!");
    }
}
