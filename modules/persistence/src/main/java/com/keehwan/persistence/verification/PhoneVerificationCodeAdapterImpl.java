package com.keehwan.persistence.verification;

import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;
import com.keehwan.core.verification.exception.VerificationNotExistsException;
import com.keehwan.core.verification.service.persistence.PhoneVerificationCodeAdapter;
import com.keehwan.persistence.verification.jpa.PhoneVerificationCodeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PhoneVerificationCodeAdapterImpl implements PhoneVerificationCodeAdapter {

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
