package com.keehwan.core.user.service.usecase;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.user.domain.User;

public interface UserReadUsecase {

    User getUserByUserAccount(UserAccount account);
}
