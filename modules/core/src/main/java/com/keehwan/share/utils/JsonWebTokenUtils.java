package com.keehwan.share.utils;

import com.keehwan.share.domain.code.JsonWebTokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JsonWebTokenUtils {
    private final String secret;
    private final SecretKey key;

    public JsonWebTokenUtils(String secret) {
        this.secret = secret;
        byte[] byteSecretKey = Decoders.BASE64.decode(this.secret);
        key = Keys.hmacShaKeyFor(byteSecretKey);
    }

    public String generate(String username, String role, JsonWebTokenType type) {
        return this.generate(username, role, type.name(), type.getExpiredMs());
    }

    public String generate(String username, String role, String type, long expiredMs) {
        return Jwts.builder()
                .claim("type", type)
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(key)
                .compact();
    }

    public Boolean isExpired(String token) {
        return getPayload(token)
                .getExpiration()
                .before(new Date());
    }

    public String getUsername(String token) {
        return getPayload(token)
                .get("username", String.class);
    }

    public boolean hasClaim(String token, String key, Object value) {
        return value.equals(this.getClaimMap(token).getOrDefault(key, null));
    }

    public Map<String, Object> getClaimMap(String token) {
        return this.getPayload(token);
    }

    private Claims getPayload(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
