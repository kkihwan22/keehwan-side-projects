package com.keehwan.share.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseCreatedAndUpdatedDateTimeWithAudit extends BaseCreatedAndUpdatedDateTime {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    @Getter
    protected Long createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    @Getter
    protected Long updatedBy;
}
