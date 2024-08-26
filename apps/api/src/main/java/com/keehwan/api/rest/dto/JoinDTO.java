package com.keehwan.api.rest.dto;

import com.keehwan.core.account.service.usecases.CreateUserAccountUsecase.UserAccountCreateCommand;

public class JoinDTO {

    public record JoinRequest(
            String username,
            String nickname,
            String password,
            String profileImage
    ) {
        public UserAccountCreateCommand toCommand() {
            return new UserAccountCreateCommand(username, nickname, password, profileImage);
        }
    }
}
