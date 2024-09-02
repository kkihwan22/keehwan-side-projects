package com.keehwan.persistence.account;

import com.keehwan.domain.account.domain.UserAccount;
import com.keehwan.domain.account.exception.UserAccountNotExistsException;
import com.keehwan.domain.account.persistence.UserAccountPersistenceAdapter;
import com.keehwan.persistence.account.jpa.UserAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAccountPersistenceAdapterImpl implements UserAccountPersistenceAdapter {

    private final UserAccountJpaRepository userAccountJpaRepository;

    @Override
    public Optional<UserAccount> findAccountByUsername(String username) {
        return userAccountJpaRepository.findUserAccountByUsername(username);
    }

    @Override
    public UserAccount getAccountByUsername(String username) {
        return this.findAccountByUsername(username)
                .orElseThrow(UserAccountNotExistsException::new);
    }

    @Override
    public UserAccount create(UserAccount account) {
        return userAccountJpaRepository.save(account);
    }
}
