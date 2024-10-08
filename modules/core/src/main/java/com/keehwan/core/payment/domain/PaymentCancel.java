package com.keehwan.core.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentCancel {
    private BigDecimal requestCanceledAmount;
    private BigDecimal canceledAmount;
    private LocalDateTime canceledDateTime;
}
