package com.keehwan.domain.account.persistence;

import com.keehwan.domain.account.domain.UserAccountDevice;
import org.jetbrains.annotations.NotNull;

public interface UserAccountDevicePersistenceAdapter {

    @NotNull UserAccountDevice create(@NotNull final UserAccountDevice userAccountDevice);
}
