package com.github.terravivaproject.terraviva.user.entities.mappers;

import com.github.terravivaproject.terraviva.user.entities.User;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAP = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    User registrationRequestDtoToUser(RegistrationRequestDto request);
}
