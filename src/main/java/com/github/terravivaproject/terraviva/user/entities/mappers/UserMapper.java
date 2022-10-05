package com.github.terravivaproject.terraviva.user.entities.mappers;

import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.dto.MinimalUserDto;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * UserMapper interface.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Mapper
public interface UserMapper {
    /**
     * Constant <code>MAP</code>
     */
    UserMapper MAP = Mappers.getMapper(UserMapper.class);

    /**
     * userToUserDto.
     *
     * @param appUser a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.dto.UserDto} object
     */
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userRole", source = "appUser.roles")
    UserDto userToUserDto(AppUser appUser);

    //AppUser userDtoToUser(UserDto userDto);

    /**
     * registrationRequestDtoToUser.
     *
     * @param request a {@link com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     */
    AppUser registrationRequestDtoToUser(RegistrationRequestDto request);

    /**
     * <p>entityToMinimalDto.</p>
     *
     * @param u a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.dto.MinimalUserDto} object
     */
    MinimalUserDto entityToMinimalDto(AppUser u);
}
