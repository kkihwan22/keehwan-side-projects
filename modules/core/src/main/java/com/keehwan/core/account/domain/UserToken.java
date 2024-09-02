package com.keehwan.core.account.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
@Getter
@Entity
@Table(name = "user_token")
public class UserToken extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Nullable Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserAccount account;

    @Column(name = "token")
    private String token;

    @Column(name = "expired")
    private boolean expired;

    @Column(name = "expired_at")
    private LocalDateTime expiredDateTime;

    @Column(name = "expired_reason")
    private String reason;

    public UserToken(UserAccount account, String token) {
        this.account = account;
        this.token = token;
    }

    public void expire(String reason) {
        this.expired = true;
        this.expiredDateTime = LocalDateTime.now();
        this.reason = reason;
    }
}
