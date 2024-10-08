package com.keehwan.persistence.account;

import com.keehwan.core.account.domain.UserToken;
import com.keehwan.core.account.exception.TokenNotExistsException;
import com.keehwan.core.account.persistence.UserTokenPersistenceAdapter;
import com.keehwan.persistence.account.jpa.UserTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserTokenPersistenceAdapterImpl implements UserTokenPersistenceAdapter {
    private final UserTokenJpaRepository userTokenJpaRepository;

    @Override
    public @NotNull UserToken create(@NotNull UserToken userToken) {
        return userTokenJpaRepository.save(userToken);
    }

    @Override
    public @NotNull UserToken getUserToken(@NotNull String token) {
        return userTokenJpaRepository.findUserTokenByToken(token)
                .orElseThrow(TokenNotExistsException::new);
    }

    @Override
    public boolean deleteUserToken(String token) {
        return false;
    }

    @Override
    public boolean deleteUserTokenByUsername(String username) {
        return false;
    }
}
