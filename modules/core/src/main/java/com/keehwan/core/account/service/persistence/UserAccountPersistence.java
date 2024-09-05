package com.keehwan.core.account.service.persistence;

import com.keehwan.core.account.domain.UserAccount;

import java.util.Optional;

public interface UserAccountPersistence {

    Optional<UserAccount> findAccountByUsername(String username);

    UserAccount getAccountByUsername(String username);

    UserAccount create(UserAccount userAccount);

}
