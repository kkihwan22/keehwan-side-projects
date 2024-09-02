package com.keehwan.domain.account.persistence;

import com.keehwan.domain.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface UserTokenPersistenceAdapter {

    @NotNull UserToken create(@NotNull UserToken userToken);

    @NotNull UserToken getUserToken(@NotNull String token);

    boolean deleteUserToken(String token);

    boolean deleteUserTokenByUsername(String username);
}
