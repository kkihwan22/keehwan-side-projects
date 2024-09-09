package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;

public interface UserAccountProfileImageDeleteUsecase {

    UserAccount deleteProfileImage(String username);
}
