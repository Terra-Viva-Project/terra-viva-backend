package com.github.terravivaproject.terraviva.user.entities.enumerations;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<UserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRole role) {
        if (role == null) {
            return null;
        }
        return role.getCode();
    }

    @Override
    public UserRole convertToEntityAttribute(Integer code) {
        if (code == null)
            return null;
        else
            return UserRole.fromCode(code);
    }
}