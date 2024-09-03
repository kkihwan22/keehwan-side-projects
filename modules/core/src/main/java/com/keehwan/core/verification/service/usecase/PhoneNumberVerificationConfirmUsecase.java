package com.keehwan.core.verification.service.usecase;

public interface PhoneNumberVerificationConfirmUsecase {

    boolean confirm(String token, String code);
}
