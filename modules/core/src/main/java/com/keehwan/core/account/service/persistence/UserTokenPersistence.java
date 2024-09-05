package com.keehwan.core.account.service.persistence;

import com.keehwan.core.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface UserTokenPersistence {

    @NotNull UserToken create(@NotNull UserToken userToken);

    @NotNull UserToken getUserToken(@NotNull String token);

    boolean deleteUserToken(String token);

    boolean deleteUserTokenByUsername(String username);
}
