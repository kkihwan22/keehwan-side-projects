package com.keehwan.fixtures;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.enums.UserAccountStatus;

public class UserAccountFixture {

    public static UserAccount getUserAccount() {
        return UserAccount.builder()
                .username("username@keehwan.com")
                .nickname("testUser")
                .password("123456")
                .profileImage(null)
                .status(UserAccountStatus.ENABLED)
                .build();
    }
}
