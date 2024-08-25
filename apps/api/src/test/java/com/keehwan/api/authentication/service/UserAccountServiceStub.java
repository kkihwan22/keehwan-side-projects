package com.keehwan.api.authentication.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.service.usecases.GetUserAccountUsecase;
import org.jetbrains.annotations.NotNull;

public class UserAccountServiceStub implements GetUserAccountUsecase {

    @Override
    public UserAccount getUserAccount(@NotNull String username) {
        return UserAccount.registerCredential("test@test.com", "1233");
    }
}
