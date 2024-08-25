package com.keehwan.core.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@ToString
public class UserAccountVerification {

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "verification_code_sent_at")
    private LocalDateTime verificationCodeSentDateTime;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "verified_at")
    private LocalDateTime verifiedDateTime;

    public static UserAccountVerification init() {
        UserAccountVerification verification = new UserAccountVerification();
        verification.verified = false;
        verification.verificationCode = "123456"; // TODO: 난수 적용
        verification.verificationCodeSentDateTime = LocalDateTime.now();
        return verification;
    }

    public boolean verifyCode(String code) {
        if (Objects.isNull(code)) return false;

        if (Objects.equals(this.verificationCode, code)) {
            this.verify();
            return true;
        }

        return false;
    }

    public void verify() {
        this.verified = true;
        this.verifiedDateTime = LocalDateTime.now();
    }
}
