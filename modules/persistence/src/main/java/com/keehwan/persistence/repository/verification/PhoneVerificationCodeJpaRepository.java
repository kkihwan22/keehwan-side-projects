package com.keehwan.persistence.repository.verification;

import com.keehwan.core.verification.domain.SendVerificationCodeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneVerificationCodeJpaRepository extends JpaRepository<SendVerificationCodeHistory, Long> {

    Optional<SendVerificationCodeHistory> findPhoneNumberVerificationCodeByToken(String token);
}
