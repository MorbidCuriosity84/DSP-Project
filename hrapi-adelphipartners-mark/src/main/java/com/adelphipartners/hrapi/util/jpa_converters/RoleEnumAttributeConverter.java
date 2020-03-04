package com.adelphipartners.hrapi.util.jpa_converters;

import com.adelphipartners.hrapi.model.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class RoleEnumAttributeConverter implements AttributeConverter<Role,
        String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role == null ? null : role.toString();
    }

    @Override
    public Role convertToEntityAttribute(String s) {
        return s == null ? null :
                Role.valueOfLabel(Arrays.asList(s.split(":")).get(0).trim());
    }
}
