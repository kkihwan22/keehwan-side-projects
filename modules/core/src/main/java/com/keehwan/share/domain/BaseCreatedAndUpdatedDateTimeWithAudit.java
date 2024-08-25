package com.keehwan.share.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


// TODO: 로그인 기능이 연동되어야 사용이 가능합니다. 로그인 기능 연동 후 구현하세요.

@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class BaseCreatedAndUpdatedDateTimeWithAudit extends BaseCreatedAndUpdatedDateTime {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected Long createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected Long updatedBy;
}
