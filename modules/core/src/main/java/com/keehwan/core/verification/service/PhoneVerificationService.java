package com.keehwan.core.verification.service;

import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;
import com.keehwan.core.verification.exception.VerificationTokenExpiredException;
import com.keehwan.core.verification.service.persistence.PhoneVerificationCodeAdapter;
import com.keehwan.core.verification.service.usecase.PhoneNumberVerificationConfirmUsecase;
import com.keehwan.core.verification.service.usecase.PhoneNumberVerificationRequestUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhoneVerificationService implements
        PhoneNumberVerificationRequestUsecase, PhoneNumberVerificationConfirmUsecase {

    private final PhoneVerificationCodeAdapter phoneVerificationCodeAdapter;

    @Override
    public PhoneNumberVerificationCode request(String phoneNumber) {
        return phoneVerificationCodeAdapter.createVerificationCode(PhoneNumberVerificationCode.issue(phoneNumber));
    }

    @Override
    public boolean confirm(String token, String code) {
        PhoneNumberVerificationCode findCode = phoneVerificationCodeAdapter.getVerificationCode(token);
        if (findCode.isExpired()) throw new VerificationTokenExpiredException();
        return findCode.verify(code);
    }
}

