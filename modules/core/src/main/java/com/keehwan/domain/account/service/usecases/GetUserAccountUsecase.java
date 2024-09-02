package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserAccount;
import org.jetbrains.annotations.NotNull;

public interface GetUserAccountUsecase {

    UserAccount getUserAccount(@NotNull String username);
}
