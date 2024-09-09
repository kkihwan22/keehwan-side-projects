package com.keehwan.core.user.service.persistence;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.user.domain.User;

public interface UserPersistence {

    User createUser(User user);
    User getUserByUserAccount(UserAccount account);
}
