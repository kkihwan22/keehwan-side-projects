package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.enums.UserAccountStatus;
import com.keehwan.core.account.exception.UserAccountAlreadyExistsException;
import com.keehwan.core.account.persistence.UserAccountPersistenceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.keehwan.core.account.service.usecases.CreateUserAccountUsecase.UserAccountCreateCommand;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @InjectMocks
    UserAccountService sut;

    @Spy
    UserAccountPersistenceStub accountPersistenceAdapter;

    String username = "init@email.com";
    String password = "1q2w3e4r";
    String nickname = "testUser";

    @Nested
    class CreateUserAccountTest {
        @BeforeEach
        void setUp() {
            accountPersistenceAdapter.create(UserAccount.registerCredential(username,nickname, password, null));
        }

        @DisplayName("이미 등록된 email이 있다면 ")
        @Test
        void case1() throws Exception {

            assertThrows(UserAccountAlreadyExistsException.class, () -> {
                sut.create(new UserAccountCreateCommand(username, nickname, password, null));
            });
        }

        @DisplayName("이미 등록된 email이 없다면 신규로 등록 ")
        @Test
        void case2() throws Exception {
            String nonExistsEmail = "seconds@email.com";

            UserAccount userAccount = accountPersistenceAdapter.create(UserAccount.registerCredential(nonExistsEmail, nickname, password, null));

            assertAll(
                    () -> assertEquals(nonExistsEmail, userAccount.getUsername()),
                    () -> assertEquals(password, userAccount.getPassword()),
                    () -> assertEquals(UserAccountStatus.ENABLED, userAccount.getStatus()),
                    () -> assertEquals(-0, userAccount.getFailureCount())
            );
        }
    }

//    @Nested
//    class AuthenticateTest {
//        String notExistEmail = "notexist@email.com";
//        String missMatchPassword = "password";
//
//        @BeforeEach
//        void setUp() {
//            accountPersistenceAdapter.create(new UserAccount(email, password));
//        }
//
//        @DisplayName("사용자를 찾을 수 없는 경우 - AccountNotExistsException 발생")
//        @Test
//        void case1() {
//            assertThrows(UserAccountNotExistsException.class,
//                    () -> sut.authenticate(notExistEmail, password));
//        }
//
//        @DisplayName("유저의 상태가 Enabled가 아닐 시 - AccountLockedException 발생")
//        @Test
//        void case2() {
//            UserAccount userAccount = accountPersistenceAdapter.getAccountByEmail(email);
//            userAccount.locked();
//            assertThrows(UserAccountLockedException.class,
//                    () -> sut.authenticate(email, password));
//        }
//
//        @DisplayName("패스워드가 불일치 시 - PasswordMissMatchException 발생")
//        @Test
//        void case3() {
//            assertThrows(UserAccountPasswordMissMatchException.class,
//                    () -> sut.authenticate(email, missMatchPassword));
//        }
//
//        @DisplayName("Success! authenticate.")
//        @Test
//        void case4() {
//
//            UserAccount result = sut.authenticate(email, password);
//            int expectedLoginFailureCount = 0;
//
//            assertAll(
//                    () -> assertNotNull(result),
//                    () -> assertEquals(email, result.getUsername()),
//                    () -> assertEquals(password, result.getPassword()),
//                    () -> assertEquals(UserAccountStatus.ENABLED, result.getStatus()),
//                    () -> assertEquals(expectedLoginFailureCount, result.getFailureCount())
//            );
//        }
//    }
}