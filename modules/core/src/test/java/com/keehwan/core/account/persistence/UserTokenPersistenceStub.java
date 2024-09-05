package com.keehwan.core.account.persistence;

import com.keehwan.core.account.domain.UserToken;
import com.keehwan.core.account.exception.TokenNotExistsException;
import com.keehwan.core.account.service.persistence.UserTokenPersistence;
import com.keehwan.share.test.exceptions.NotImplementedTestException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTokenPersistenceStub implements UserTokenPersistence {
    private final List<UserToken> entities = new ArrayList<>();

    @Override
    public @NotNull UserToken create(@NotNull UserToken userToken) {
        entities.add(userToken);
        return userToken;
    }

    @Override
    public @NotNull UserToken getUserToken(String token) {
        return this.internalFindByToken(token).orElseThrow(TokenNotExistsException::new);
    }

    private Optional<UserToken> internalFindByToken(String token) {
        return entities.stream()
                .filter(entity -> entity.getToken().equals(token))
                .findFirst();
    }

    @Override
    public boolean deleteUserToken(String token) {
        throw new NotImplementedTestException();
    }

    @Override
    public boolean deleteUserTokenByUsername(String username) {
        throw new NotImplementedTestException();
    }
}
