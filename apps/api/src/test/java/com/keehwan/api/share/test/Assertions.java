package com.keehwan.api.share.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.keehwan.api.consts.ErrorCodes;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class Assertions {
    private static final long SUCCESS_CODE = 0L;
    private static final String SUCCESS_MSG = "success";

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    public static void assertMvcErrorEquals(
            MvcResult result, ErrorCodes errorCodes
    ) throws UnsupportedEncodingException, JsonProcessingException {
        final String content = result.getResponse().getContentAsString();
        final var responseBody = objectMapper.readTree(content);

        assertEquals(errorCodes.code, responseBody.get("code").asLong());
        assertEquals(errorCodes.message, responseBody.get("message").asText());
    }

    public static void assertMvcDataEquals(
            MvcResult result, Consumer<JsonNode> consumer
    ) throws UnsupportedEncodingException, JsonProcessingException {
        final String content = result.getResponse().getContentAsString();
        final var responseBody = objectMapper.readTree(content);
        final var dataField = responseBody.get("data");

        assertNotNull(dataField);
        assertTrue(dataField.isObject());

        consumer.accept(dataField);
    }
}
