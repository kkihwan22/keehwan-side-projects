package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;

public interface UserAccountProfileImageUpdateUsecase {

    UserAccount updateProfileImage(String username, String profileImage);
}
