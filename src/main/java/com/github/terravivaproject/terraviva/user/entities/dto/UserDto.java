package com.github.terravivaproject.terraviva.user.entities.dto;

import com.github.terravivaproject.terraviva.resources.validations.UuidValidation;
import com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * UserDto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto extends BasicUserDto {
    @UuidValidation
    @Schema(example = "d88e65f0-190f-47a4-84dd-768e6d3d5c2e")
    private UUID id;

    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    @Schema(example = "04-set-2022")
    private LocalDate birthDate;


    private LocalDateTime subscribedOn;

    @Schema(example = "true")
    private Boolean verified;

    @Schema(example = "false")
    private Boolean locked;

    @Schema(example = """
            Ciao! Mi piace coltivare diverse varietà di peperoncini e pomodori.
            Se volete scambiare le sementi non avete che da contattarmi :)
            """)
    private String bio;

    @ArraySchema(
            schema = @Schema(implementation = UserRole.class),
            arraySchema = @Schema(implementation = Set.class),
            uniqueItems = true
    )
    private Collection<UserRole> userRole;
}
