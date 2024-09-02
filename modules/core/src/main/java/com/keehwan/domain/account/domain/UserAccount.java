package com.keehwan.domain.account.domain;

import com.keehwan.domain.account.domain.converters.UserAccountStatusConverter;
import com.keehwan.domain.account.domain.converters.UserRolesConverter;
import com.keehwan.domain.account.domain.enums.UserAccountStatus;
import com.keehwan.domain.account.domain.enums.UserRole;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTime;
import com.keehwan.share.utils.RandomCodeGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_account")
@DynamicInsert
@DynamicUpdate
@Builder
@Getter
@ToString
public class UserAccount extends BaseCreatedAndUpdatedDateTime {
    public enum SocialProvider {
        NONE, GOOGLE, NAVER
    }

    public final static int LOCKED_COUNT = 5;
    private final static long changePasswordDays = 90l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;  // email

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "credential_account")
    private boolean credentialAccount;

    @Column(name = "social_account")
    private boolean socialAccount;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_login_provider")
    private SocialProvider socialLoginProvider;

    @Column(name = "provider_id")
    private String providerId;

    @Convert(converter = UserRolesConverter.class)
    @Column(name = "user_roles")
    private List<UserRole> roles;

    @Builder.Default
    @Column(name = "login_failure_count")
    private int failureCount = 0;
    private boolean locked;

    @Column(name = "locked_at")
    private LocalDateTime lockedDateTime;

    @Convert(converter = UserAccountStatusConverter.class)
    private UserAccountStatus status;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "verified_at")
    private LocalDateTime verifiedDateTime;

    @Column(name = "password_last_changed_at")
    private LocalDateTime passwordLastChangedDateTime;

    public static UserAccount registerCredential(String username, String nickname, String password, String profileImage) {
        return UserAccount.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .profileImage(profileImage)
                .credentialAccount(true)
                .socialAccount(false)
                .roles(List.of(UserRole.USER))
                .build();
    }

    public static UserAccount registerSocial(
            String username,
            String nickname,
            String profileImage,
            SocialProvider socialLoginProvider,
            String providerId) {
        return UserAccount.builder()
                .username(username)
                .nickname(nickname)
                .profileImage(profileImage)
                .password(RandomCodeGenerator.generateRandomNumberString(32))
                .credentialAccount(false)
                .socialAccount(true)
                .roles(List.of(UserRole.USER))
                .verified(true)
                .verifiedDateTime(LocalDateTime.now())
                .socialLoginProvider(socialLoginProvider)
                .providerId(providerId)
                .build();
    }

    public void successPasswordMatched() {
        this.failureCount = 0;
    }

    public void failurePasswordMatched() {
        this.failureCount++;
        if (failureCount >= LOCKED_COUNT) {
            this.locked();
        }
    }

    public void locked() {
        this.locked = true;
        this.lockedDateTime = LocalDateTime.now();
    }

    public void unlocked() {
        this.locked = false;
        this.lockedDateTime = null;
    }

    public void changePassword(String password) {
        Objects.requireNonNull(password, () -> {
            throw new IllegalArgumentException("전달받은 패스워드가 null입니다.");
        });

        this.password = password;
        this.passwordLastChangedDateTime = LocalDateTime.now();
    }

    public boolean isAfterPasswordChangedAt() {
        return changePasswordDays < ChronoUnit.DAYS.between(this.passwordLastChangedDateTime, LocalDateTime.now());
    }

    public boolean isEnabled() {
        return this.status == UserAccountStatus.ENABLED;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void deleteProfileImage() {
        this.changeProfileImage(null);
    }

    public void confirmEmail() {
        this.verified = true;
        this.verifiedDateTime = LocalDateTime.now();
    }
}
