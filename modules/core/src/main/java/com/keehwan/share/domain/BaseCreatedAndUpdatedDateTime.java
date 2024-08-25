package com.keehwan.share.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseCreatedAndUpdatedDateTime {

    @Column(name = "created_at")
    @Getter
    protected LocalDateTime createdDateTime;

    @Column(name = "updated_at")
    @Getter
    protected LocalDateTime updatedDateTime;
}