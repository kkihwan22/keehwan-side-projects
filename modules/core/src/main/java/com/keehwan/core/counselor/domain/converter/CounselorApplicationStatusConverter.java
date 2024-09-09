package com.keehwan.core.counselor.domain.converter;


import com.keehwan.core.counselor.domain.enums.CounselorApplicationStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class CounselorApplicationStatusConverter implements AttributeConverter<CounselorApplicationStatus, String> {

    @Override
    public String convertToDatabaseColumn(CounselorApplicationStatus attribute) {
        return Objects.nonNull(attribute)
                ? attribute.name()
                : null;
    }

    @Override
    public CounselorApplicationStatus convertToEntityAttribute(String dbData) {
        return CounselorApplicationStatus.valueOf(dbData);
    }
}
