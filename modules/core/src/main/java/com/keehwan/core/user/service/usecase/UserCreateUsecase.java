package com.keehwan.core.user.service.usecase;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.user.domain.User;

public interface UserCreateUsecase {

    User createUser(UserCreateCommand command);

    record UserCreateCommand(String name, String phoneNumber, UserAccount account) {

        public User toEntity() {
            return new User(null, name, phoneNumber, account);
        }
    }
}
