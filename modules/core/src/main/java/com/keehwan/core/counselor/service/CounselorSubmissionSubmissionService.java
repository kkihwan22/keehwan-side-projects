package com.keehwan.core.counselor.service;

import com.keehwan.core.counselor.domain.CounselorSubmission;
import com.keehwan.core.counselor.service.persistence.CounselorSubmissionPersistence;
import com.keehwan.core.counselor.service.usecase.CounselorSubmissionApproveUsecase;
import com.keehwan.core.counselor.service.usecase.CounselorSubmissionCreateUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CounselorSubmissionSubmissionService implements
        CounselorSubmissionCreateUsecase, CounselorSubmissionApproveUsecase {
    private final CounselorSubmissionPersistence counselorSubmissionPersistence;

    @Override
    public CounselorSubmission submit(CounselorSubmissionCreateCommand command) {
        return counselorSubmissionPersistence.create(command.toEntity());
    }

    @Transactional
    @Override
    public void approve(Long id, String comment) {
        CounselorSubmission counselorSubmission = counselorSubmissionPersistence.getCounselorSubmission(id);
        counselorSubmission.approve(comment);
    }
}
