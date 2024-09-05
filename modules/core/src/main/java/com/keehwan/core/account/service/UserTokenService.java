package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.UserToken;
import com.keehwan.core.account.exception.TokenAlreadyExpiredException;
import com.keehwan.core.account.service.persistence.UserTokenPersistence;
import com.keehwan.core.account.service.usecases.CreateUserTokenUsecase;
import com.keehwan.core.account.service.usecases.ExpireUserTokenUsecase;
import com.keehwan.core.account.service.usecases.GetUserTokenUsecase;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserTokenService
        implements CreateUserTokenUsecase, ExpireUserTokenUsecase, GetUserTokenUsecase {

    private final UserTokenPersistence userTokenPersistence;

    @Override
    public @NotNull UserToken create(@NotNull UserAccount account, @NotNull String token) {
        return userTokenPersistence.create(new UserToken(account, token));
    }

    @Override
    public @NotNull UserToken expire(String token) {
        UserToken userToken = userTokenPersistence.getUserToken(token);

        if (userToken.isExpired()) {
            throw new TokenAlreadyExpiredException();
        }

        userToken.expire("Logout");

        return userToken;
    }

    @Override
    public @NotNull UserToken getUserToken(@NotNull String token) {
        return userTokenPersistence.getUserToken(token);
    }
}
