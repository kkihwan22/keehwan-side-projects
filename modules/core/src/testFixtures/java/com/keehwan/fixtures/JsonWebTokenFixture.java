package com.keehwan.fixtures;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.share.domain.code.JsonWebTokenType;
import com.keehwan.share.utils.JsonWebTokenUtils;

public class JsonWebTokenFixture {
    private static final String tempSecretKey = "tHisIsASampleJwtSecretKeyPleaseDontUseThisInProduction";
    private static final JsonWebTokenUtils jsonWebTokenUtils = new JsonWebTokenUtils(tempSecretKey);

    public static String getAccessToken() {
        UserAccount account = UserAccountFixture.getUserAccount();
        return jsonWebTokenUtils.generate(account.getUsername(), "USER", JsonWebTokenType.ACCESS);
    }
}
