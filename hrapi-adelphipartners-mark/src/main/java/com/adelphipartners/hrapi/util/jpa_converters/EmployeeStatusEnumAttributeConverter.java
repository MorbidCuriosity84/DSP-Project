package com.adelphipartners.hrapi.util.jpa_converters;


import com.adelphipartners.hrapi.model.EmployeeStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EmployeeStatusEnumAttributeConverter implements AttributeConverter<EmployeeStatus, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        return employeeStatus == null ? null : employeeStatus.toString();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(String s) {
        return s == null ? null : EmployeeStatus.valueOf(s);
    }
}
