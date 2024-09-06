package com.keehwan.api.rest.dto;


import jakarta.validation.constraints.NotBlank;

public class VerificationDTO {

    // TODO: phoneNumber annotation 적용
    public record PhoneNumberVerificationCodeRequest(
            @NotBlank String phoneNumber
    ) {}

    public record PhoneNumberVerificationConfirmCodeRequest(
            @NotBlank String token,
            @NotBlank String code
   ) {}

    public record VerificationConfirmResponse(
            boolean result, boolean expired
    ) {}
}
