package com.keehwan.share.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Converter
public class StringsConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (!attribute.isEmpty()) {
            return attribute.stream().collect(Collectors.joining(","));
        }

        return null;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (Objects.nonNull(dbData) && dbData.length() > 0) {
            return Arrays.stream(dbData.split(",")).toList();
        }

        return List.of();
    }
}
