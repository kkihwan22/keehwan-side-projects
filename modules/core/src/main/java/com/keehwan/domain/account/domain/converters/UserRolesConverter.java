package com.keehwan.domain.account.domain.converters;

import com.keehwan.domain.account.domain.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Converter
public class UserRolesConverter implements AttributeConverter<List<UserRole>, String> {

    @Override
    public String convertToDatabaseColumn(List<UserRole> attribute) {
        if (!CollectionUtils.isEmpty(attribute)) {
            return attribute.stream().map(UserRole::name).collect(Collectors.joining(","));
        }

        return null;
    }

    @Override
    public List<UserRole> convertToEntityAttribute(String dbData) {

        if (Objects.nonNull(dbData)) {
            return Arrays.stream(dbData.split(",")).map(role -> UserRole.valueOf(role)).collect(Collectors.toList());
        }

        return List.of();
    }
}
