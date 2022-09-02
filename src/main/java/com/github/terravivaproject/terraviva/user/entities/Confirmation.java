package com.github.terravivaproject.terraviva.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Confirmation {
    @Id
    @NotNull
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotBlank
    @NotNull
    private String token;

    @NotNull
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @NotNull
    private LocalTime expirationTime = LocalTime.of(0, 30);

    @NotNull
    private LocalDateTime confirmationTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
