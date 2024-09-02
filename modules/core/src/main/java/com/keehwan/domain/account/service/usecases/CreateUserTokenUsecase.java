package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserAccount;
import com.keehwan.domain.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface CreateUserTokenUsecase {

    @NotNull UserToken create(@NotNull UserAccount account, @NotNull String refreshToken);
}
