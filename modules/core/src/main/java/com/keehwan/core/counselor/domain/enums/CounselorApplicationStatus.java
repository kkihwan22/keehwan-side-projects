package com.keehwan.core.counselor.domain.enums;

import lombok.Getter;

public enum CounselorApplicationStatus {

    RECEIVED("접수"),
    UNDER_REVIEW("확인 중"),
    APPROVED("승인"),
    REJECTED("거절"),
    PENDING("보류");

    @Getter
    private final String label;

    CounselorApplicationStatus(String label) {
        this.label = label;
    }
}
