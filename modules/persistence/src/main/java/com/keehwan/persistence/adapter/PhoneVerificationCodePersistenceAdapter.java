package com.keehwan.persistence.adapter;

import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;
import com.keehwan.core.verification.exception.VerificationNotExistsException;
import com.keehwan.core.verification.service.persistence.PhoneVerificationCodePersistence;
import com.keehwan.persistence.repository.verification.PhoneVerificationCodeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PhoneVerificationCodePersistenceAdapter implements PhoneVerificationCodePersistence {

    private final PhoneVerificationCodeJpaRepository phoneVerificationCodeJpaRepository;

    @Override
    public PhoneNumberVerificationCode createVerificationCode(PhoneNumberVerificationCode phoneNumberVerificationCode) {
        return phoneVerificationCodeJpaRepository.save(phoneNumberVerificationCode);
    }

    public PhoneNumberVerificationCode getVerificationCode(String token) {
        return phoneVerificationCodeJpaRepository.findPhoneNumberVerificationCodeByToken(token)
                .orElseThrow(VerificationNotExistsException::new);
    }
}
