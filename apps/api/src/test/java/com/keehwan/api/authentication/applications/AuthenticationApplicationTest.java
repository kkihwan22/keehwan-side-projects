package com.keehwan.api.authentication.applications;

import com.keehwan.api.authentication.service.UserAccountServiceStub;
import com.keehwan.core.account.exception.UserAccountNotExistsException;
import com.keehwan.share.domain.code.JsonWebTokenType;
import com.keehwan.share.test.exceptions.NotImplementedTestException;
import com.keehwan.share.utils.JsonWebTokenUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AuthenticationApplicationTest {

    @InjectMocks
    AuthenticationApplication sut;

    @Spy
    UserAccountServiceStub userAccountServiceStub;

    @Nested
    class GetUserAccountTest {

        String secret = "tHisIsASampleJwtSecretKeyPleaseDontUseThisInProduction"; // TODO: 생성하는 Util로 분리
        JsonWebTokenUtils jsonWebTokenUtils ;
        String username = "test@test.com";
        String token;

        @BeforeEach
        void setUp() {
            jsonWebTokenUtils = new JsonWebTokenUtils(secret);
            token = jsonWebTokenUtils.generate(username, JsonWebTokenType.ACCESS);
        }


        @Disabled
        @DisplayName("토큰의 Username에 사용자계정이 존재하지 않을 때 - UsernameNotFoundException 발생.")
        @Test
        void case1() {
            // when
            doThrow(new UserAccountNotExistsException())
                    .when(userAccountServiceStub)
                    .getUserAccount(username);

            // then
            assertThrows(
                    UsernameNotFoundException.class,
                    () -> sut.getUserAccount(token));
        }

        @Disabled
        @Test
        void case2() {
             throw new NotImplementedTestException();
        }
    }
}