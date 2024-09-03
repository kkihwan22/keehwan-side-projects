package com.keehwan.core.verification.service.usecase;

import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;

public interface PhoneNumberVerificationRequestUsecase {

    PhoneNumberVerificationCode request(String phoneNumber);
}
