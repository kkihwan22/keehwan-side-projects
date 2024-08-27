package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;

public interface UserAccountUpdateNickname {

    UserAccount updateNickname(String username, String nickname);
}
