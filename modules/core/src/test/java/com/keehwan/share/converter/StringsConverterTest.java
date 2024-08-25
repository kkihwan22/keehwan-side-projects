package com.keehwan.share.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringsConverterTest {

    StringsConverter sut = new StringsConverter();

    @DisplayName("attribute (리스트)가 빈 경우 - null을 리턴한다.")
    @Test
    void case1() {
        List<String> attribute = List.of();
        String actual = sut.convertToDatabaseColumn(attribute);
        assertNull(actual);
    }

    @DisplayName("attribute (리스트)가 하나일 때 정상적으로 string 을 리턴한다.")
    @Test
    void case2() {
        String expected = "하나";
        List<String> attribute = List.of(expected);
        String actual = sut.convertToDatabaseColumn(attribute);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @DisplayName("attribute (리스트)가 여러개일 때 정상적으로 string,으로 연결 후 리턴한다.")
    @Test
    void case3() {
        String expected = "하나,둘,셋";
        List<String> attribute = List.of("하나", "둘", "셋");
        String actual = sut.convertToDatabaseColumn(attribute);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @DisplayName("String이 null 인 경우 빈 리스트를 리턴한다.")
    @Test
    void case4() {
        List<String> actual = sut.convertToEntityAttribute(null);
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    @DisplayName("String이 한단어 인 경우 list의 사이즈는 하나이다.")
    @Test
    void case5() {
        List<String> actual = sut.convertToEntityAttribute("하나");
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @DisplayName("String이 ,로 연결된 단어인 경우 list의 사이즈는 1보다 크다")
    @Test
    void case6() {
        List<String> actual = sut.convertToEntityAttribute("하나,둘,셋");
        assertNotNull(actual);
        assertEquals(3, actual.size());
    }
}