package com.adelphipartners.hrapi.util.jpa_converters;

import com.adelphipartners.hrapi.model.OfficeLocation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class OfficeLocationEnumAttributeConverter implements AttributeConverter<OfficeLocation, String> {
    @Override
    public String convertToDatabaseColumn(OfficeLocation officeLocation) {
        return officeLocation == null ? null : officeLocation.toString();
    }

    @Override
    public OfficeLocation convertToEntityAttribute(String s) {
        return s == null ? null : OfficeLocation.valueOfFullLocation(s);
    }
}
