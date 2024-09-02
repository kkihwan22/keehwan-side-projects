package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface GetUserTokenUsecase {

    @NotNull UserToken getUserToken(@NotNull String token);
}
