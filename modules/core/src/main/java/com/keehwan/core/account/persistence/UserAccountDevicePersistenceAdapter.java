package com.keehwan.core.account.persistence;

import com.keehwan.core.account.domain.UserAccountDevice;
import org.jetbrains.annotations.NotNull;

public interface UserAccountDevicePersistenceAdapter {

    @NotNull UserAccountDevice create(@NotNull final UserAccountDevice userAccountDevice);
}
