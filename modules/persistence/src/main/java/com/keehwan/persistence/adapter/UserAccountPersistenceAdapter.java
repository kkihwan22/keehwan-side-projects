package com.keehwan.persistence.adapter;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.exception.UserAccountNotExistsException;
import com.keehwan.core.account.service.persistence.UserAccountPersistence;
import com.keehwan.persistence.repository.account.UserAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAccountPersistenceAdapter implements UserAccountPersistence {

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
