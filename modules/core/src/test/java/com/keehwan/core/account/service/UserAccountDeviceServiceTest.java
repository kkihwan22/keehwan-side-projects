package com.keehwan.core.account.service;

import com.keehwan.core.account.domain.UserAccountDevice;
import com.keehwan.core.account.persistence.UserAccountDevicePersistenceStub;
import com.keehwan.share.domain.code.Browser;
import com.keehwan.share.domain.code.OperationSystem;
import com.keehwan.share.test.exceptions.NotImplementedTestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserAccountDeviceServiceTest {

    @InjectMocks
    UserAccountDeviceService sut;

    @Spy
    UserAccountDevicePersistenceStub userAccountDevicePersistenceAdapter;

    @Nested
    class IssueUserAccountDeviceIDTest {

        @DisplayName("유저의 디바이스ID를 정상적으로 생성한다.")
        @Test
        void case1() {

            //given
            OperationSystem os = OperationSystem.WINDOW;
            Browser browser = Browser.EDGE;

            //when
            UserAccountDevice result = sut.create(os, browser);

            //then
            Assertions.assertThat(result).isNotNull();
            Assertions.assertThat(result.getDeviceId()).isNotNull();
            Assertions.assertThat(result.getOs()).isEqualTo(os);
            Assertions.assertThat(result.getBrowser()).isEqualTo(browser);
        }
    }

    @Nested
    class FindUserAccountDeviceTest {

        @DisplayName("")
        @Test
        void case1() {
            throw new NotImplementedTestException();
        }

    }

}