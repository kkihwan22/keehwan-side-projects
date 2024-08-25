package com.keehwan.share.utils;

import java.util.UUID;

public class UUIDGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String withoutBar() {
        return UUIDGenerator.generate().replaceAll("-", "");
    }
}
