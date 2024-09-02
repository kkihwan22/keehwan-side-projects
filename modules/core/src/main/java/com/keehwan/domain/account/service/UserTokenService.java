package com.keehwan.domain.account.service;

import com.keehwan.domain.account.domain.UserAccount;
import com.keehwan.domain.account.domain.UserToken;
import com.keehwan.domain.account.exception.TokenAlreadyExpiredException;
import com.keehwan.domain.account.persistence.UserTokenPersistenceAdapter;
import com.keehwan.domain.account.service.usecases.CreateUserTokenUsecase;
import com.keehwan.domain.account.service.usecases.ExpireUserTokenUsecase;
import com.keehwan.domain.account.service.usecases.GetUserTokenUsecase;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserTokenService
        implements CreateUserTokenUsecase, ExpireUserTokenUsecase, GetUserTokenUsecase {

    private final UserTokenPersistenceAdapter userTokenPersistenceAdapter;

    @Override
    public @NotNull UserToken create(@NotNull UserAccount account, @NotNull String token) {
        return userTokenPersistenceAdapter.create(new UserToken(account, token));
    }

    @Override
    public @NotNull UserToken expire(String token) {
        UserToken userToken = userTokenPersistenceAdapter.getUserToken(token);

        if (userToken.isExpired()) {
            throw new TokenAlreadyExpiredException();
        }

        userToken.expire("Logout");

        return userToken;
    }

    @Override
    public @NotNull UserToken getUserToken(@NotNull String token) {
        return userTokenPersistenceAdapter.getUserToken(token);
    }
}
