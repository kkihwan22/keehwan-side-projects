package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccountDevice;
import com.keehwan.core.account.service.persistence.UserAccountDevicePersistence;
import com.keehwan.core.account.service.usecases.CreateUserAccountDeviceUsecase;
import com.keehwan.share.domain.code.Browser;
import com.keehwan.share.domain.code.OperationSystem;
import com.keehwan.share.utils.UUIDGenerator;
import org.jetbrains.annotations.NotNull;

public class UserAccountDeviceService
        implements CreateUserAccountDeviceUsecase {

    private final UserAccountDevicePersistence userAccountDevicePersistence;

    public UserAccountDeviceService(UserAccountDevicePersistence userAccountDevicePersistence) {
        this.userAccountDevicePersistence = userAccountDevicePersistence;
    }

    @Override
    public @NotNull UserAccountDevice create(@NotNull OperationSystem os, @NotNull Browser browser) {

        String uuid = UUIDGenerator.withoutBar();
        return userAccountDevicePersistence.create(new UserAccountDevice(uuid, os, browser));
    }
}
