package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.exception.UserAccountAlreadyExistsException;
import com.keehwan.core.account.persistence.UserAccountPersistenceAdapter;
import com.keehwan.core.account.service.usecases.CreateUserAccountUsecase;
import com.keehwan.core.account.service.usecases.GetUserAccountUsecase;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserAccountService implements
        CreateUserAccountUsecase, GetUserAccountUsecase {

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
}
