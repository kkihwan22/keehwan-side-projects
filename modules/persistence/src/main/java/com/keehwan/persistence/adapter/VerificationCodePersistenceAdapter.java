package com.keehwan.persistence.adapter;

import com.keehwan.core.verification.domain.SendVerificationCodeHistory;
import com.keehwan.core.verification.exception.VerificationNotExistsException;
import com.keehwan.core.verification.service.persistence.VerificationCodePersistence;
import com.keehwan.persistence.repository.verification.PhoneVerificationCodeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VerificationCodePersistenceAdapter implements VerificationCodePersistence {

    private final PhoneVerificationCodeJpaRepository phoneVerificationCodeJpaRepository;

    @Override
    public SendVerificationCodeHistory createVerificationCode(SendVerificationCodeHistory sendVerificationCodeHistory) {
        return phoneVerificationCodeJpaRepository.save(sendVerificationCodeHistory);
    }

    public SendVerificationCodeHistory getVerificationCode(String token) {
        return phoneVerificationCodeJpaRepository.findPhoneNumberVerificationCodeByToken(token)
                .orElseThrow(VerificationNotExistsException::new);
    }
}
