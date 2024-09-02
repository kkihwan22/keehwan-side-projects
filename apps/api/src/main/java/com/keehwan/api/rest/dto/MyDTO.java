package com.keehwan.api.rest.dto;

import com.keehwan.domain.account.domain.UserAccount;
import jakarta.validation.constraints.NotBlank;

public class MyDTO {

    public record NicknameUpdateRequest(@NotBlank String nickname) {}
    public record ProfileImageUpdateRequest(@NotBlank String profileImage) {}

    public record MyResponse(Long id, String username, String nickname, String profileImage) {
        public static MyResponse of(UserAccount account) {
            return new MyResponse(account.getId(), account.getUsername(), account.getNickname(), account.getProfileImage());
        }
    }
}
