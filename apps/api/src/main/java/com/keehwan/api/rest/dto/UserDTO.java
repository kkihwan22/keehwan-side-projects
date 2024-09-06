package com.keehwan.api.rest.dto;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.user.service.usecase.UserCreateUsecase.UserCreateCommand;

public class UserDTO {

    public record UserCreateRequest(
            String name, String phoneNumber, String token
    ) {
        public UserCreateCommand toCommand(UserAccount account) {
            return new UserCreateCommand(name, phoneNumber, account);
        }
    }
}
