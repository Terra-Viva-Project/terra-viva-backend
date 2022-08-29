package com.github.terravivaproject.terraviva.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @NotBlank @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birthDate;

    @NotBlank @NotNull
    private LocalDateTime subscribedOn = LocalDateTime.now();

    @NotBlank @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private Boolean verified;

    private String bio;

    //TODO implements @Pattern -> URI
    private String profileImage;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Variety.class)
    //TODO Understand if we need another enity to add data to the relations
    //TODO Understand Cascade type
    private List<Variety> varieties;
}
