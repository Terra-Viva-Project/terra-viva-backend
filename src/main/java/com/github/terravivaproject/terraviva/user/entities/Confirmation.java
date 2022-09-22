package com.github.terravivaproject.terraviva.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Confirmation {
    @Id
    @NotNull
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID token;

    @NotNull
    private LocalDateTime creationTimestamp = LocalDateTime.now();

    @NotNull
    private LocalDateTime expirationTime = LocalDateTime.now()
            .plusMinutes(30);

    @Setter
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
