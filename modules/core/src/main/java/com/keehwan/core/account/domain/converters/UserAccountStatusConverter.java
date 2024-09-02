package com.keehwan.core.account.domain.converters;


import com.keehwan.core.account.domain.enums.UserAccountStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class UserAccountStatusConverter implements AttributeConverter<UserAccountStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserAccountStatus attribute) {
        return Objects.nonNull(attribute)
                ? attribute.name()
                : null;
    }

    @Override
    public UserAccountStatus convertToEntityAttribute(String dbData) {
        return UserAccountStatus.get(dbData);
    }
}
