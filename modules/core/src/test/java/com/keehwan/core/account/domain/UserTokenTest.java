package com.keehwan.core.account.domain;

import com.keehwan.fixtures.UserAccountFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTokenTest {

    UserAccount account;

    @BeforeEach
    void setUp() {
        account = UserAccountFixture.getUserAccount();
    }

    @DisplayName("토큰이 생성된다.")
    @Test
    void case1() {
        UserToken token = new UserToken(account, "sAmplEToken12334");

        assertNotNull(token.getAccount());
        assertNotNull(token.getToken());
        assertEquals(token.getToken(), "sAmplEToken12334");
        assertFalse(token.isExpired());
        assertNull(token.getExpiredDateTime());
    }
}