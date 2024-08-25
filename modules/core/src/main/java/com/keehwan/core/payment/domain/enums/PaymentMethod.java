package com.keehwan.core.payment.domain.enums;

import lombok.Getter;

public enum PaymentMethod {

    CARD("신용카드"),
    KAKAO("카카오페이"), NAVER("네이버페이"), PAYCO("페이코"),
    VACCT("무통장입금"),
    AUTO_PAY("자동(정기)결제"),
    EASY_PAY("간편결제"),
    ;

    @Getter
    String label;

    PaymentMethod(String label) {
        this.label = label;
    }
}
