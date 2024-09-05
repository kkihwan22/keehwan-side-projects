package com.keehwan.share.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class JsonConverter<T> implements AttributeConverter<T, String> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Class<T> type;

    public JsonConverter(Class<T> type) {
        this.type = type;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (Objects.isNull(attribute)) return null;

        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;

        try {
            return mapper.readValue(dbData, type);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }
}
