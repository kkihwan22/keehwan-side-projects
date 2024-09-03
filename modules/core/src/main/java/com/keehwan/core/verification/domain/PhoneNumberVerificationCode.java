package com.keehwan.core.verification.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTime;
import com.keehwan.share.utils.RandomCodeGenerator;
import com.keehwan.share.utils.UUIDGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "phone_verification_code_history")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor @AllArgsConstructor
@Getter @ToString
public class PhoneNumberVerificationCode extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "token")
    private String token;

    @Column(name = "code")
    private String code;

    @Column(name = "expiration_at")
    private LocalDateTime expirationDateTime;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "verified_at")
    private LocalDateTime verifiedDateTime;

    public static PhoneNumberVerificationCode issue(String phoneNumber) {
        PhoneNumberVerificationCode phoneNumberVerificationCode = new PhoneNumberVerificationCode();
        phoneNumberVerificationCode.phoneNumber = phoneNumber;
        phoneNumberVerificationCode.token = UUIDGenerator.withoutBar();
        phoneNumberVerificationCode.code = RandomCodeGenerator.generateRandomNumberString(6);
        phoneNumberVerificationCode.expirationDateTime = LocalDateTime.now().plusMinutes(4);
        phoneNumberVerificationCode.verified = false;
        return phoneNumberVerificationCode;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expirationDateTime);
    }

    public boolean verify(String code) {
        if (!this.code.equals(code)) return false;

        this.verified = true;
        this.verifiedDateTime = LocalDateTime.now();
        return true;
    }
}
