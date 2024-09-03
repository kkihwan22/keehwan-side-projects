package com.keehwan.api.rest;

import com.keehwan.api.application.VerificationApplication;
import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.rest.dto.VerificationDTO.PhoneNumberVerificationConfirmResponse;
import com.keehwan.api.share.BaseRestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.keehwan.api.rest.dto.VerificationDTO.PhoneNumberVerificationCodeRequest;
import static com.keehwan.api.rest.dto.VerificationDTO.PhoneNumberVerificationConfirmCodeRequest;

@RequiredArgsConstructor
@RestController
public class VerificationRestController implements BaseRestController {
    private final VerificationApplication verificationApplication;

    @PostMapping("/api/v1/verifications/phone")
    public ApiResponse<String> requestPhoneVerificationCode(@RequestBody @Valid PhoneNumberVerificationCodeRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        String token = verificationApplication.requestVerificationCode(request.phoneNumber());
        return ApiResponse.just(token);
    }

    @PatchMapping("/api/v1/verifications/phone/verify-code")
    public ApiResponse<PhoneNumberVerificationConfirmResponse> verifyCode(@RequestBody @Valid PhoneNumberVerificationConfirmCodeRequest request, BindingResult bindingResult) {
        hasError(bindingResult);
        return ApiResponse.just(verificationApplication.confirmVerificationCode(request.token(), request.code()));
    }
}
