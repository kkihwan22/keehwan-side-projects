package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;
import org.jetbrains.annotations.NotNull;

public interface CreateUserAccountUsecase {

    @NotNull UserAccount create(@NotNull final String email, @NotNull final String password);
}
