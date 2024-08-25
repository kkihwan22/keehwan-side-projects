package com.keehwan.share.utils;


import com.keehwan.share.domain.code.JsonWebTokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JsonWebTokenUtils {
    private final String secret;
    private final SecretKey key;

    public JsonWebTokenUtils(String secret) {
        this.secret = secret;
        byte[] byteSecretKey = Decoders.BASE64.decode(this.secret);
        key = Keys.hmacShaKeyFor(byteSecretKey);
    }

    public String generate(String username, String role, JsonWebTokenType type) {
        return Jwts.builder()
                .claim("type", type.name())
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + type.getExpiredMs()))
                .signWith(key)
                .compact();
    }

    public Boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }


    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }
}
