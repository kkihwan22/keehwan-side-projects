package com.keehwan.api.rest.facade;

import com.keehwan.api.authentication.support.SecurityContextSupporter;
import com.keehwan.api.rest.dto.UserDTO.UserCreateRequest;
import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.service.usecases.GetUserAccountUsecase;
import com.keehwan.core.user.domain.User;
import com.keehwan.core.user.service.usecase.UserCreateUsecase;
import com.keehwan.core.verification.domain.SendVerificationCodeHistory;
import com.keehwan.core.verification.service.usecase.VerificationCodeSelectUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserApplication {
    private final GetUserAccountUsecase getUserAccountUsecase;
    private final UserCreateUsecase userCreateUsecase;
    private final VerificationCodeSelectUsecase verificationCodeSelectUsecase;

    @Transactional
    public void createUser(UserCreateRequest request) {
        UserAccount account = getUserAccountUsecase.getUserAccount(SecurityContextSupporter.getUsername());
        User createUser = userCreateUsecase.createUser(request.toCommand(account));

        SendVerificationCodeHistory history = verificationCodeSelectUsecase.getHistoryByToken(request.token());

        // TODO: 아직 isVerified == false 이면 throw exception.
        if (!history.isVerified()) {
            //
        }

        account.confirmPhoneNumber();
    }
}
