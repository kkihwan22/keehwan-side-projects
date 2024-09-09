package com.keehwan.persistence.adapter;

import com.keehwan.core.counselor.domain.CounselorSubmission;
import com.keehwan.core.counselor.exception.CounselorSubmissionNotFoundException;
import com.keehwan.core.counselor.service.persistence.CounselorSubmissionPersistence;
import com.keehwan.persistence.repository.counselor.CounselorSubmissionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CounselorSubmissionPersistenceAdapter implements CounselorSubmissionPersistence {
    private final CounselorSubmissionJpaRepository counselorSubmissionJpaRepository;

    @Override
    public CounselorSubmission create(CounselorSubmission counselorSubmission) {
        return counselorSubmissionJpaRepository.save(counselorSubmission);
    }

    @Override
    public CounselorSubmission getCounselorSubmission(Long id) {
        return counselorSubmissionJpaRepository
                .findById(id)
                .orElseThrow(CounselorSubmissionNotFoundException::new);
    }
}
