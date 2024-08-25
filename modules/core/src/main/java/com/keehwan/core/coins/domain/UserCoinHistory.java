package com.keehwan.core.coins.domain;

import com.keehwan.core.coins.domain.enums.UserCoinHistoryType;
import com.keehwan.core.user.domain.User;
import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.math.BigDecimal;

public class UserCoinHistory extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;
    private User user;
    private UserCoinHistoryType type;
    private BigDecimal amount;
    private BigDecimal balance;

    private String reason;
    private String pgRequestId;
}