package com.keehwan.core.account.domain;

import com.keehwan.core.account.domain.enums.UserAccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UserAccountTest {

    @Test
    void lockedTest() {
        UserAccount userAccount = new UserAccount();
        userAccount.locked();
        assertThat(userAccount.isLocked()).isTrue();
    }

    @Nested
    class VerifyPasswordTest {
        UserAccount userAccount;
        String password = "1q2w3e4r";
        String missMatchedPassword = password.concat(password);

        @BeforeEach
        void setUp() {
            userAccount = UserAccount.registerCredential("init@email.com", password);
        }

        @DisplayName("패스워드 불일치 시 - failureCount를 1증가시키고, false를 반환한다.")
        @Test
        void missMatchPasswordTest() {
            int expectedFailureCount = userAccount.getFailureCount() + 1;

            // when
            userAccount.failurePasswordMatched();

            // then
            assertThat(userAccount.getFailureCount()).isEqualTo(expectedFailureCount);
            assertThat(userAccount.getStatus()).isEqualTo(UserAccountStatus.ENABLED);
        }

        @DisplayName("패스워드 불일치 시 5회 - AccountStatus를 LOCKED으로 변경 후., false를 반환한다.")
        @Test
        void missMatchPasswordOver5Test() {
            // when
            for (int i = 0; i < UserAccount.LOCKED_COUNT + 1; i++) {
                userAccount.failurePasswordMatched();
            }

            // then
            assertThat(userAccount.getFailureCount() >= UserAccount.LOCKED_COUNT).isTrue();
            assertThat(userAccount.isLocked()).isTrue();
        }

        @DisplayName("패스워드 일치 시 - failureCount를 0으로 초기화, true 반환한다.")
        @Test
        void matchPasswordTest() {
            int expectedFailureCount = 0;

            // when
            userAccount.successPasswordMatched();

            // then
            assertThat(userAccount.getFailureCount()).isEqualTo(expectedFailureCount);
            assertThat(userAccount.getStatus()).isEqualTo(UserAccountStatus.ENABLED);
        }

        @DisplayName("로그인 실패 횟수 초과로 상태가 LOCKED 인 경우 - false를 반환한다. ")
        @Test
        void alreadyStatusIsLockedTest() {
            userAccount.locked();

            // when
            userAccount.failurePasswordMatched();

            // then
            assertThat(userAccount.isLocked()).isTrue();
        }
    }

    @Nested
    class PasswordChangedTest {

        String username = "username";
        String password = "password";

        UserAccount account;
        LocalDateTime now = LocalDateTime.now();

        @BeforeEach
        void setUp() {
            this.account = UserAccount.registerCredential(username, password);
        }

        @DisplayName("마지막 패스워드 변경 날짜가 오늘보다 90일 전이면 false를 반환한다.")
        @Test
        void case1() throws IllegalAccessException, NoSuchFieldException {
            // given
            declaredFiledPasswordLastChangedDateTime(10L);

            //when
            boolean result = account.isAfterPasswordChangedAt();
            assertThat(result).isFalse();
        }

        @DisplayName("마지막 패스워드 변경 날짜가 오늘보다 90일이면 false를 반환한다.")
        @Test
        void case2() throws IllegalAccessException, NoSuchFieldException {
            // given
            declaredFiledPasswordLastChangedDateTime(90L);

            //when
            boolean result = account.isAfterPasswordChangedAt();
            assertThat(result).isFalse();
        }

        @DisplayName("마지막 패스워드 변경 날짜가 오늘보다 90일 후이면 true를 반환한다.")
        @Test
        void case3() throws IllegalAccessException, NoSuchFieldException {
            // given
            declaredFiledPasswordLastChangedDateTime(92L);

            //when
            boolean result = account.isAfterPasswordChangedAt();
            assertThat(result).isTrue();
        }

        @DisplayName("변경할 패스워드가 null이면 IllegalArgumentException 발생한다. ")
        @Test
        void case4() {
            // given
            UserAccount account = UserAccount.registerCredential(username, password);
            String message = "전달받은 패스워드가 null입니다.";

            // then
            assertThatThrownBy((() -> {
                account.changePassword(null);
            })).isInstanceOf(IllegalArgumentException.class).hasMessage(message);
        }

        @DisplayName("패스워드 변경 성공")
        @Test
        void case5() {
            // given
            UserAccount account = UserAccount.registerCredential(username, password);

            // when
            String changedPassword = "changedPassword";
            account.changePassword(changedPassword);

            // then
            assertThat(account.getPassword()).isEqualTo(changedPassword);
            assertThat(account.getPasswordLastChangedDateTime()).isAfter(now);
        }

        private void declaredFiledPasswordLastChangedDateTime(long days) throws NoSuchFieldException, IllegalAccessException {
            Field passwordLastChangedDateTimeField = UserAccount.class.getDeclaredField("passwordLastChangedDateTime");
            passwordLastChangedDateTimeField.setAccessible(true);
            passwordLastChangedDateTimeField.set(account, now.minusDays(days));
        }
    }
}