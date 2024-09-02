package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserAccount;

public interface UserAccountUpdateNickname {

    UserAccount updateNickname(String username, String nickname);
}
