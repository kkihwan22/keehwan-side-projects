package com.keehwan.core.account.persistence;

import com.keehwan.core.account.domain.UserAccount;

import java.util.Optional;

public interface UserAccountPersistenceAdapter {

    Optional<UserAccount> findAccountByUsername(String username);

    UserAccount getAccountByUsername(String username);

    UserAccount create(UserAccount userAccount);

}
