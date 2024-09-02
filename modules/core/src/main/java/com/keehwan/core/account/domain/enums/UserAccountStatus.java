package com.keehwan.core.account.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserAccountStatus {
    ENABLED,
    BLOCKED,
    WITHDRAW,
    DORMANT,
    ;

    private static final Map<String, UserAccountStatus> lookup =
            Arrays.stream(UserAccountStatus.values()).collect(Collectors.toMap(UserAccountStatus::name, Function.identity()));

    public static UserAccountStatus get(String code) {
        return lookup.getOrDefault(code, null);
    }
}
