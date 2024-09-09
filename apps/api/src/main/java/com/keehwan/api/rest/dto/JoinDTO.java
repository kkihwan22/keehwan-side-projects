package com.keehwan.api.rest.dto;

import com.keehwan.core.account.service.usecases.UserAccountCreateUsecase.UserAccountCreateCommand;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JoinDTO {

    public record JoinRequest(
            String username,
            String nickname,
            String password,
            String profileImage
    ) {
        public UserAccountCreateCommand toCommand(PasswordEncoder encoder) {
            return new UserAccountCreateCommand(username, nickname, encoder.encode(password), profileImage);
        }
    }
}
