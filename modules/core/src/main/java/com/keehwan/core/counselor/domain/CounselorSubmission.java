package com.keehwan.core.counselor.domain;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.counselor.domain.converter.CounselorApplicationStatusConverter;
import com.keehwan.core.counselor.domain.enums.CounselorApplicationStatus;
import com.keehwan.core.counselor.exception.CounselorSubmissionIllegalStatusException;
import com.keehwan.core.user.domain.User;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "counselor_submission")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class CounselorSubmission extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private UserAccount account;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "reason", columnDefinition = "text")
    private String reason;

    @Convert(converter = CounselorApplicationStatusConverter.class)
    @Column(name = "status")
    private CounselorApplicationStatus status;

    @Column(name = "reason", columnDefinition = "text")
    private String comment;

    public CounselorSubmission(UserAccount account, User user, String reason) {
        this.account = account;
        this.user = user;
        this.reason = reason;
        this.status = CounselorApplicationStatus.RECEIVED;
    }

    public void approve(String comment) {
        if (status != CounselorApplicationStatus.RECEIVED) {
            throw new CounselorSubmissionIllegalStatusException();
        }

        this.status = CounselorApplicationStatus.APPROVED;
        this.comment = comment;
    }
}
