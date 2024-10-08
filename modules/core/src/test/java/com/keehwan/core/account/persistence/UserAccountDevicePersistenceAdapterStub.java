package com.keehwan.core.account.persistence;

import com.keehwan.core.account.domain.UserAccountDevice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserAccountDevicePersistenceAdapterStub implements UserAccountDevicePersistenceAdapter {
    private final List<UserAccountDevice> entities = new ArrayList<>();


    @Override
    public @NotNull UserAccountDevice create(@NotNull UserAccountDevice userAccountDevice) {
        entities.add(userAccountDevice);
        return userAccountDevice;
    }
}
