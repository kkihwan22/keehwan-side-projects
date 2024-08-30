package com.keehwan.fixtures;

import com.keehwan.core.account.domain.UserToken;

public class UserTokenFixture {

    public static UserToken getUserToken() {
        return new UserToken(UserAccountFixture.getUserAccount(), JsonWebTokenFixture.getAccessToken());
    }
}
