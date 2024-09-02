package com.keehwan.core.account.service.usecases;

import com.keehwan.core.account.domain.UserAccount;
import org.jetbrains.annotations.NotNull;

public interface CreateUserAccountUsecase {

    @NotNull UserAccount create(UserAccountCreateCommand command);

    record UserAccountCreateCommand(String username, String nickname, String password, String profileImage) {

        public UserAccount toEntity() {
            return UserAccount.registerCredential(username, nickname, password, profileImage);
        }
    }
}
