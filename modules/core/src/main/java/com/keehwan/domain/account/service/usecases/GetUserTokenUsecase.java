package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface GetUserTokenUsecase {

    @NotNull UserToken getUserToken(@NotNull String token);
}
