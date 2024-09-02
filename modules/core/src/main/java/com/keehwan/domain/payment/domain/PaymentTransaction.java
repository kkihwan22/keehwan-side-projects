package com.keehwan.domain.payment.domain;

import com.keehwan.domain.payment.domain.enums.PaymentMethod;
import com.keehwan.domain.payment.domain.enums.PaymentStatus;
import com.keehwan.domain.payment.domain.enums.PaymentType;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.math.BigDecimal;

public class PaymentTransaction extends BaseCreatedAndUpdatedDateTimeWithAudit {

    private Long id;
    private Long userId;
    private String requestId;
    private String transactionId;
    private PaymentMethod paymentMethod;
    private PaymentType type;
    private BigDecimal amount;

    private String cardCompanyCode;
    private int installment;

    private PaymentStatus status;
    private String errorCode;
    private String errorMessage;

    private Vacct vacct;
    private PaymentCancel cancel;
}
