package com.keehwan.api.rest.facade;

import com.keehwan.api.rest.dto.VerificationDTO.VerificationConfirmResponse;
import com.keehwan.core.verification.domain.SendVerificationCodeHistory;
import com.keehwan.core.verification.service.usecase.VerificationCodeSelectUsecase;
import com.keehwan.core.verification.service.usecase.VerificationRequestUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VerificationApplication {
    private final VerificationRequestUsecase verificationRequestUsecase;
    private final VerificationCodeSelectUsecase verificationCodeSelectUsecase;

    public String requestVerificationCode(String phoneNumber) {
        SendVerificationCodeHistory sendVerificationCodeHistory = verificationRequestUsecase.request(phoneNumber);
        // todo: sms 발송
        return sendVerificationCodeHistory.getToken();
    }

    // TODO : 응답 값 변경 ( result, reason )
    public VerificationConfirmResponse confirmVerificationCode(String token, String code) {
        SendVerificationCodeHistory history = verificationCodeSelectUsecase.getHistoryByToken(token);

        if (history.isExpired()) {
            return new VerificationConfirmResponse(false, true);
        }

        return new VerificationConfirmResponse(history.verify(code), false);
    }
}
