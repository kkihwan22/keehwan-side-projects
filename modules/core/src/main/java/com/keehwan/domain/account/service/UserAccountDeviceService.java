package com.keehwan.domain.account.service;

import com.keehwan.domain.account.domain.UserAccountDevice;
import com.keehwan.domain.account.persistence.UserAccountDevicePersistenceAdapter;
import com.keehwan.domain.account.service.usecases.CreateUserAccountDeviceUsecase;
import com.keehwan.share.domain.code.Browser;
import com.keehwan.share.domain.code.OperationSystem;
import com.keehwan.share.utils.UUIDGenerator;
import org.jetbrains.annotations.NotNull;

public class UserAccountDeviceService
        implements CreateUserAccountDeviceUsecase {

    private final UserAccountDevicePersistenceAdapter userAccountDevicePersistenceAdapter;

    public UserAccountDeviceService(UserAccountDevicePersistenceAdapter userAccountDevicePersistenceAdapter) {
        this.userAccountDevicePersistenceAdapter = userAccountDevicePersistenceAdapter;
    }

    @Override
    public @NotNull UserAccountDevice create(@NotNull OperationSystem os, @NotNull Browser browser) {

        String uuid = UUIDGenerator.withoutBar();
        return userAccountDevicePersistenceAdapter.create(new UserAccountDevice(uuid, os, browser));
    }
}
