package com.github.terravivaproject.terraviva.user.entities.mappers;

import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAP = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userRole", source = "appUser.roles")
    UserDto userToUserDto(AppUser appUser);

    //AppUser userDtoToUser(UserDto userDto);

    AppUser registrationRequestDtoToUser(RegistrationRequestDto request);
}