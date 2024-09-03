package com.keehwan.api.application;

import com.keehwan.api.rest.dto.VerificationDTO.PhoneNumberVerificationConfirmResponse;
import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;
import com.keehwan.core.verification.exception.VerificationTokenExpiredException;
import com.keehwan.core.verification.service.usecase.PhoneNumberVerificationConfirmUsecase;
import com.keehwan.core.verification.service.usecase.PhoneNumberVerificationRequestUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VerificationApplication {
    private final PhoneNumberVerificationRequestUsecase phoneNumberVerificationRequestUsecase;
    private final PhoneNumberVerificationConfirmUsecase phoneNumberVerificationConfirmUsecase;

    public String requestVerificationCode(String phoneNumber) {
        PhoneNumberVerificationCode phoneNumberVerificationCode = phoneNumberVerificationRequestUsecase.request(phoneNumber);
        // todo: sms 발송
        return phoneNumberVerificationCode.getToken();
    }

    public PhoneNumberVerificationConfirmResponse confirmVerificationCode(String token, String code) {
        try {
            boolean result = phoneNumberVerificationConfirmUsecase.confirm(token, code);
            return new PhoneNumberVerificationConfirmResponse(result, false);
        } catch (VerificationTokenExpiredException e) {
            return new PhoneNumberVerificationConfirmResponse(false, true);
        }
    }
}
