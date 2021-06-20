package com.katenemochka.schoollights.utils;

import com.katenemochka.schoollights.domain.DayOffType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DaysOffTypeConverter implements AttributeConverter<DayOffType, String> {

    @Override
    public String convertToDatabaseColumn(DayOffType dayOffType) {
        if (dayOffType == null) {
            return null;
        }
        return dayOffType.getDisplayName();
    }

    @Override
    public DayOffType convertToEntityAttribute(String dayOffType) {
        if (dayOffType == null) {
            return null;
        }
        return Stream.of(DayOffType.values())
                .filter(c -> c.getDisplayName().equals(dayOffType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}