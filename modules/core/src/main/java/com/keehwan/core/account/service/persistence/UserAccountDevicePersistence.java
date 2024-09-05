package com.keehwan.core.account.service.persistence;

import com.keehwan.core.account.domain.UserAccountDevice;
import org.jetbrains.annotations.NotNull;

public interface UserAccountDevicePersistence {

    @NotNull UserAccountDevice create(@NotNull final UserAccountDevice userAccountDevice);
}
