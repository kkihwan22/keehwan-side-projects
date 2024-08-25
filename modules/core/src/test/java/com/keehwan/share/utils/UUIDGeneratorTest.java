package com.keehwan.share.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UUIDGeneratorTest {

    @Nested
    class UUIDTest {

        @DisplayName("UUID를 생성한다.")
        @Test
        void case1() {
            String uuid = UUIDGenerator.generate();

            int expectedSize = 36;
            assertEquals(expectedSize, uuid.length());
            assertTrue(uuid.contains("-"));
        }

        @DisplayName("-가 없는 UUID를 생성한다.")
        @Test
        void case2() {

            // when
            String withoutBar = UUIDGenerator.withoutBar();

            int expectedSize = 32;
            assertEquals(expectedSize, withoutBar.length());
            assertFalse(withoutBar.contains("-"));
        }
    }

}