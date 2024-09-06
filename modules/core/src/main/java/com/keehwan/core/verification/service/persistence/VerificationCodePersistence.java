package com.keehwan.core.verification.service.persistence;

import com.keehwan.core.verification.domain.SendVerificationCodeHistory;

public interface VerificationCodePersistence {

    SendVerificationCodeHistory createVerificationCode(SendVerificationCodeHistory sendVerificationCodeHistory);

    SendVerificationCodeHistory getVerificationCode(String token);
}
