package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface CreateUserTokenUsecase {

    @NotNull UserToken create(@NotNull UserAccount account, @NotNull String refreshToken);
}
