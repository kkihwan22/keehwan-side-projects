package com.keehwan.domain.account.service.usecases;

import com.keehwan.domain.account.domain.UserAccountDevice;
import com.keehwan.share.domain.code.Browser;
import com.keehwan.share.domain.code.OperationSystem;
import org.jetbrains.annotations.NotNull;

public interface CreateUserAccountDeviceUsecase {

    @NotNull UserAccountDevice create(@NotNull final OperationSystem operationSystem, @NotNull final Browser browser);
}
