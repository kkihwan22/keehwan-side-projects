package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;

public interface UserAccountEmailConfirmUsecase {

    UserAccount confirmEmail(String username);
}
