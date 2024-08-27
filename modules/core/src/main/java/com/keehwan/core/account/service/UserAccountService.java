package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.exception.UserAccountAlreadyExistsException;
import com.keehwan.core.account.persistence.UserAccountPersistenceAdapter;
import com.keehwan.core.account.service.usecases.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserAccountService implements
        CreateUserAccountUsecase, GetUserAccountUsecase, UserAccountUpdateNickname, UserAccountUpdateProfileImage, UserAccountDeleteProfileImage {

    private final UserAccountPersistenceAdapter userAccountPersistenceAdapter;

    @Override
    public @NotNull UserAccount create(UserAccountCreateCommand command) {
        UserAccount findAccount = userAccountPersistenceAdapter.findAccountByUsername(command.username())
                .orElse(null);

        if (Objects.nonNull(findAccount)) {
            throw new UserAccountAlreadyExistsException();
        }

        return userAccountPersistenceAdapter.create(command.toEntity());
    }

    @Override
    public UserAccount getUserAccount(@NotNull String username) {
        return userAccountPersistenceAdapter.getAccountByUsername(username);
    }

    @Transactional
    @Override
    public UserAccount deleteProfileImage(String username) {
        UserAccount account = this.getUserAccount(username);
        account.deleteProfileImage();
        return account;
    }

    @Override
    public UserAccount updateNickname(String username, String nickname) {
        UserAccount account = this.getUserAccount(username);
        account.changeNickname(nickname);
        return account;
    }

    @Override
    public UserAccount updateProfileImage(String username, String profileImage) {
        UserAccount account = this.getUserAccount(username);
        account.changeProfileImage(profileImage);
        return account;
    }
}
