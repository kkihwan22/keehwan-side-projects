package com.keehwan.share.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecretKeyGenerator {
    private final static SignatureAlgorithm DEFAULT_ALGORITHM = SignatureAlgorithm.HS256;

    public static Key generate() {
        return SecretKeyGenerator.generate(SignatureAlgorithm.HS256);
    }

    public static Key generate(SignatureAlgorithm algorithm) {
        return Keys.secretKeyFor(algorithm);
    }
}
