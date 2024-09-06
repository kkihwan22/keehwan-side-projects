package com.keehwan.core.verification.service;

import com.keehwan.core.verification.domain.SendVerificationCodeHistory;
import com.keehwan.core.verification.service.persistence.VerificationCodePersistence;
import com.keehwan.core.verification.service.usecase.VerificationCodeSelectUsecase;
import com.keehwan.core.verification.service.usecase.VerificationRequestUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerificationCodeService implements
        VerificationRequestUsecase, VerificationCodeSelectUsecase {

    private final VerificationCodePersistence verificationCodePersistence;

    @Override
    public SendVerificationCodeHistory request(String phoneNumber) {
        return verificationCodePersistence.createVerificationCode(SendVerificationCodeHistory.issue(phoneNumber));
    }

    @Override
    public SendVerificationCodeHistory getHistoryByToken(String token) {
        return verificationCodePersistence.getVerificationCode(token);
    }
}

