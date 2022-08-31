package com.github.terravivaproject.terraviva.user.entities.dto;

import com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto extends BasicUserDto {
    private UUID id;
    private LocalDate birthDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime subscribedOn;
    private Boolean verified;
    private Boolean locked;
    private String bio;
    private UserRole userRole;
}