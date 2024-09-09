package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;
import org.jetbrains.annotations.NotNull;

public interface UserAccountReadUsecase {

    UserAccount getUserAccount(@NotNull String username);
}
