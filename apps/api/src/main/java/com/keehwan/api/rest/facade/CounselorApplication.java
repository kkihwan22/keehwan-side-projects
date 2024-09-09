package com.keehwan.api.rest.facade;

import com.keehwan.api.authentication.support.SecurityContextSupporter;
import com.keehwan.api.rest.dto.CounselorDTO.CounselorSubmissionCreateRequest;
import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.service.usecases.UserAccountReadUsecase;
import com.keehwan.core.counselor.domain.CounselorSubmission;
import com.keehwan.core.counselor.service.usecase.CounselorSubmissionCreateUsecase;
import com.keehwan.core.user.domain.User;
import com.keehwan.core.user.service.usecase.UserReadUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CounselorApplication {
    private final CounselorSubmissionCreateUsecase counselorSubmissionCreateUsecase;
    private final UserAccountReadUsecase accountReadUsecase;
    private final UserReadUsecase userReadUsecase;

    @Transactional
    public void createCounselorSubmission(CounselorSubmissionCreateRequest request) {
        UserAccount account = accountReadUsecase.getUserAccount(SecurityContextSupporter.getUsername());
        User user = userReadUsecase.getUserByUserAccount(account);
        CounselorSubmission submission = counselorSubmissionCreateUsecase.submit(request.toCommand(account, user));
    }

}
