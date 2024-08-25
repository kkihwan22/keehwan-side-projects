package com.keehwan.core.account.persistence;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.exception.UserAccountNotExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAccountPersistenceAdapterStub implements UserAccountPersistenceAdapter {
    private final List<UserAccount> userAccountEntities = new ArrayList<>();

    @Override
    public Optional<UserAccount> findAccountByUsername(String username) {
        return this.internalFindByEmail(username);
    }

    @Override
    public UserAccount getAccountByUsername(String username) {
        return this.internalFindByEmail(username)
                .orElseThrow(UserAccountNotExistsException::new);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        userAccountEntities.add(userAccount);
        return userAccount;
    }

    private Optional<UserAccount> internalFindByEmail(String email) {
        return userAccountEntities.stream()
                .filter(entity -> entity.getUsername().equals(email))
                .findFirst();
    }
}
