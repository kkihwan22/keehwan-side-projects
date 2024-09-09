package com.keehwan.persistence.repository.counselor;

import com.keehwan.core.counselor.domain.CounselorSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselorSubmissionJpaRepository extends JpaRepository<CounselorSubmission, Long> {
}
