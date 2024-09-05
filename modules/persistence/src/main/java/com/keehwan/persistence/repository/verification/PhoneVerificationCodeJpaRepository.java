package com.keehwan.persistence.repository.verification;

import com.keehwan.core.verification.domain.PhoneNumberVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneVerificationCodeJpaRepository extends JpaRepository<PhoneNumberVerificationCode, Long> {

    Optional<PhoneNumberVerificationCode> findPhoneNumberVerificationCodeByToken(String token);
}
