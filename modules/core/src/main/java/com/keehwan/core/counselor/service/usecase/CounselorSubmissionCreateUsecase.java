package com.keehwan.core.counselor.service.usecase;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.counselor.domain.CounselorSubmission;
import com.keehwan.core.user.domain.User;

public interface CounselorSubmissionCreateUsecase {

    CounselorSubmission submit(CounselorSubmissionCreateCommand command);

    record CounselorSubmissionCreateCommand(
            UserAccount account, User user, String reason
    ) {
        public CounselorSubmission toEntity() {
            return new CounselorSubmission(account, user, reason);
        }
    }
}
