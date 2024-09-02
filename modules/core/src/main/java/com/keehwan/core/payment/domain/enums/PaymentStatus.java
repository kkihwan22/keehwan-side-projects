package com.keehwan.core.payment.domain.enums;

import lombok.Getter;

public enum PaymentStatus {

    PENDING("입금대기"), EXCEEDED("입금 기간초과"), COMPLETED("결제완료"), CANCELED("결제취소"), FAILED("결제실패"),

    ;

    @Getter
    String label;

    PaymentStatus(String label) {
        this.label = label;
    }
}
