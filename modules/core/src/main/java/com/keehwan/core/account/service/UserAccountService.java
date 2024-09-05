package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.exception.UserAccountAlreadyExistsException;
import com.keehwan.core.account.service.persistence.UserAccountPersistence;
import com.keehwan.core.account.service.usecases.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserAccountService implements
        CreateUserAccountUsecase, GetUserAccountUsecase, UserAccountUpdateNickname, UserAccountUpdateProfileImage, UserAccountDeleteProfileImage, UserAccountEmailConfirmUsecase {

    private final UserAccountPersistence userAccountPersistence;

    @Override
    public @NotNull UserAccount create(UserAccountCreateCommand command) {
        UserAccount findAccount = userAccountPersistence.findAccountByUsername(command.username())
                .orElse(null);

        if (Objects.nonNull(findAccount)) {
            throw new UserAccountAlreadyExistsException();
        }

        return userAccountPersistence.create(command.toEntity());
    }

    @Override
    public UserAccount getUserAccount(@NotNull String username) {
        return userAccountPersistence.getAccountByUsername(username);
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

    @Transactional
    @Override
    public UserAccount confirmEmail(String username) {
        UserAccount findUserAccount = userAccountPersistence.getAccountByUsername(username);
        findUserAccount.confirmEmail();
        return findUserAccount;
    }
}
