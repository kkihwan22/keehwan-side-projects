package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserToken;
import org.jetbrains.annotations.NotNull;

public interface ExpireUserTokenUsecase {

    @NotNull UserToken expire(@NotNull String token);
}
