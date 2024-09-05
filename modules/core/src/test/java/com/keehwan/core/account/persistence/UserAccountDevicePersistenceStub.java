package com.keehwan.core.account.persistence;

import com.keehwan.core.account.domain.UserAccountDevice;
import com.keehwan.core.account.service.persistence.UserAccountDevicePersistence;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserAccountDevicePersistenceStub implements UserAccountDevicePersistence {
    private final List<UserAccountDevice> entities = new ArrayList<>();


    @Override
    public @NotNull UserAccountDevice create(@NotNull UserAccountDevice userAccountDevice) {
        entities.add(userAccountDevice);
        return userAccountDevice;
    }
}
