package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserAccount;

public interface UserAccountUpdateProfileImage {

    UserAccount updateProfileImage(String username, String profileImage);
}
