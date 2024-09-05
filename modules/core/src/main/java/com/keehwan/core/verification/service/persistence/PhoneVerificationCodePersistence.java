package com.keehwan.core.verification.service.persistence;

import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;

public interface PhoneVerificationCodePersistence {

    PhoneNumberVerificationCode createVerificationCode(PhoneNumberVerificationCode phoneNumberVerificationCode);

    PhoneNumberVerificationCode getVerificationCode(String token);
}
