package com.keehwan.core.user.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.user.domain.User;
import com.keehwan.core.user.service.persistence.UserPersistence;
import com.keehwan.core.user.service.usecase.UserCreateUsecase;
import com.keehwan.core.user.service.usecase.UserReadUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserCreateUsecase, UserReadUsecase {
    private final UserPersistence userPersistence;

    @Override
    public User createUser(UserCreateCommand command) {
        return userPersistence.createUser(command.toEntity());
    }

    @Override
    public User getUserByUserAccount(UserAccount account) {
        return userPersistence.getUserByUserAccount(account);
    }
}
