package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserAccount;

public interface UserAccountEmailConfirmUsecase {

    UserAccount confirmEmail(String username);
}
