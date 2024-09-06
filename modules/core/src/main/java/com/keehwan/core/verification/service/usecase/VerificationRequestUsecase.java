package com.keehwan.core.verification.service.usecase;

import com.keehwan.core.verification.domain.SendVerificationCodeHistory;

public interface VerificationRequestUsecase {

    SendVerificationCodeHistory request(String phoneNumber);
}
