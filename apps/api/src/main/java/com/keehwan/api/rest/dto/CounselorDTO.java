package com.keehwan.api.rest.dto;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.counselor.service.usecase.CounselorSubmissionCreateUsecase.CounselorSubmissionCreateCommand;
import com.keehwan.core.user.domain.User;
import jakarta.validation.constraints.NotBlank;

public class CounselorDTO {

    public record CounselorSubmissionCreateRequest(
            @NotBlank String reason
    ) {
        public CounselorSubmissionCreateCommand toCommand(UserAccount account, User user) {
            return new CounselorSubmissionCreateCommand(account, user, reason);
        }
    }
}
