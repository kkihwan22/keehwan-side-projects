package com.keehwan.share.utils;

import com.keehwan.share.domain.code.JsonWebTokenType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonWebTokenUtilsTest {

    private JsonWebTokenUtils jwtUtil;
    private final String secret = "verysecretkeyforjwtgenerationwhichmustbeverylong";
    private String token;
    private final String username = "testuser";
    private final String role = "USER";

    @Nested
    class TokenCreateTest {

        @BeforeEach
        void setUp() {
            jwtUtil = new JsonWebTokenUtils(secret);
        }

        @ParameterizedTest
        @EnumSource(JsonWebTokenType.class)
        void testGenerateToken(JsonWebTokenType type) {
            String token = jwtUtil.generate(username, role, type);
            assertNotNull(token);
            assertFalse(token.isEmpty());
        }
    }

    @Nested
    class TokenExceptionTest {
        private String badSecret = "bad2secretkeyforjwtgenerationwhichmustbeverylong";

        @BeforeEach
        void setUp() {
            jwtUtil = new JsonWebTokenUtils(secret);
        }

        @DisplayName("토큰의 signature가 다르면 - SignatureException 예외를 발생한다.")
        @Test
        void case1() {
            JsonWebTokenUtils differentJwtUtils = new JsonWebTokenUtils(badSecret);
            String badToken = differentJwtUtils.generate("testuser", role, JsonWebTokenType.ACCESS);
            jwtUtil = new JsonWebTokenUtils(secret);
            Assertions.assertThrows(SignatureException.class, () -> jwtUtil.getUsername(badToken));
        }

        @DisplayName("토큰의 유효기간이 이미 지난 경우 - ExpiredJwtException.class")
        @Test
        void case2() {
            String alreadyExpiredToken = jwtUtil.generate(username, role, JsonWebTokenType.ACCESS.name(), (-60 * 1000L));
            Assertions.assertThrows(ExpiredJwtException.class, () -> jwtUtil.getUsername(alreadyExpiredToken));
        }
    }

    @Nested
    class TokenPayloadTest {
        @BeforeEach
        void setUp() {
            jwtUtil = new JsonWebTokenUtils(secret);
        }

        @DisplayName("GetClaims 시 Map<String,Object> 로 값이 잘 넘어온다.")
        @Test
        void case1() {

            String token = jwtUtil.generate(username, role, JsonWebTokenType.ACCESS);
            Map<String, Object> claimMap = jwtUtil.getClaimMap(token);

            assertNotNull(claimMap);
            assertEquals(claimMap.get("type"), JsonWebTokenType.ACCESS.name());
            assertEquals(claimMap.get("username"), username);
            assertEquals(claimMap.get("role"), role);
        }

        @Test
        void case2() {
            String token = jwtUtil.generate(username, role, JsonWebTokenType.ACCESS);
            assertTrue(jwtUtil.hasClaim(token, "type", JsonWebTokenType.ACCESS.name()));
            assertTrue(jwtUtil.hasClaim(token, "username", username));
            assertTrue(jwtUtil.hasClaim(token, "role", role));
            assertFalse(jwtUtil.hasClaim(token, "noKey", "noKey"));
        }

    }
}