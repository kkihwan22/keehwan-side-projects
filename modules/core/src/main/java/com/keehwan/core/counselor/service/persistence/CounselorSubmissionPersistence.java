package com.keehwan.core.counselor.service.persistence;

import com.keehwan.core.counselor.domain.CounselorSubmission;

public interface CounselorSubmissionPersistence {

    CounselorSubmission create(CounselorSubmission counselorSubmission);
    CounselorSubmission getCounselorSubmission(Long id);
}
