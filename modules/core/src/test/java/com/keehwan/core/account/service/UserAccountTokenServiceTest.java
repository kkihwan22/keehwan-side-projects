package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.UserToken;
import com.keehwan.core.account.exception.TokenAlreadyExpiredException;
import com.keehwan.core.account.exception.TokenNotExistsException;
import com.keehwan.core.account.persistence.UserTokenPersistenceAdapterStub;
import com.keehwan.fixtures.UserAccountFixture;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserAccountTokenServiceTest {
    private final static String username = "account@email.com";
    private final static String nickname = "testUser";
    private final static String password = "testUser1234";
    private final static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    private final static String notExistToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_cccccccc";

    @InjectMocks
    UserTokenService sut;

    @Spy
    UserTokenPersistenceAdapterStub userAccountPersistenceAdapterStub;

    UserAccount account;


    // 로그인 시, 생성한 refresh token을 저장한다.
    @Nested
    class IssueTokenTest {

        @BeforeEach
        void setUp() {
            account = UserAccountFixture.getUserAccount();
        }

        @DisplayName("로그인 시 생성된 refresh token을 저장한다.")
        @Test
        void case1() {
            verifyTokenCreation();
        }
    }

    @Nested
    class ExpireRefreshTokenTest {

        @BeforeEach
        void setUp() {
            account = UserAccountFixture.getUserAccount();
            userAccountPersistenceAdapterStub.create(new UserToken(account, token));
        }

        @DisplayName("expire 할 refresh token을 찾을 수 없는 경우 - TokenNotExistsException 예외를 발생")
        @Test
        void case1() {

            Assertions.assertThrows(TokenNotExistsException.class,
                    () -> sut.expire(notExistToken));
        }

        @DisplayName("expire 할 refresh token이 이미 expired 된 경우 - TokenNotExistsException 예외를 발생")
        @Test
        void case2() {

            UserToken expiredUserToken = new UserToken(account, token);
            expiredUserToken.expire("Logout");

            doReturn(expiredUserToken)
                    .when(userAccountPersistenceAdapterStub)
                    .getUserToken(anyString());


            assertThrows(TokenAlreadyExpiredException.class, () -> sut.expire(token));

            verify(userAccountPersistenceAdapterStub).getUserToken(token);
        }

        @DisplayName("refresh token을 정상적으로 expired 한다.")
        @Test
        void case3() {

            // when
            UserToken expiredUserToken = sut.expire(token);

            LocalDateTime expectedExpiredDay = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);

            assertAll(
                    () -> assertTrue(expiredUserToken.isExpired()),
                    () -> assertNotNull(expiredUserToken.getExpiredDateTime()),
                    () -> assertEquals(expectedExpiredDay, expiredUserToken.getExpiredDateTime().truncatedTo(ChronoUnit.DAYS))
            );
        }
    }

    // token을 이용해서 access token 재발급하기
    @Nested
    class ReIssueTokenTest {

        @BeforeEach
        void setUp() {
            account = UserAccountFixture.getUserAccount();
            userAccountPersistenceAdapterStub.create(new UserToken(account, token));
        }

        @DisplayName("전달된 refresh token이 존재하지 않을 경우 - TokenNotExistsException 예외를 발생")
        @Test
        void case1() {
            assertThrows(TokenNotExistsException.class , () -> sut.getUserToken(notExistToken));
        }

        @DisplayName("전달된 refresh token이 존재하면 ")
        @Test
        void case2() {
            verifyTokenCreation();
        }
    }

    private void verifyTokenCreation() {
        UserToken tokens = sut.create(account, token);

        int expectedTokenSize = token.length();

        assertAll(
                () -> assertNotNull(tokens),
                () -> assertNotNull(tokens.getToken()),
                () -> assertEquals(expectedTokenSize, tokens.getToken().length()),
                () -> assertNull(tokens.getExpiredDateTime()),
                () -> assertFalse(tokens.isExpired())
        );
    }
}
