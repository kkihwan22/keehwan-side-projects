package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;

public interface UserAccountUpdateProfileImage {

    UserAccount updateProfileImage(String username, String profileImage);
}
