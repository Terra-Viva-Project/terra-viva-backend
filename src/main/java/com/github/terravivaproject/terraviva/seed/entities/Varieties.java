package com.github.terravivaproject.terraviva.seed.entities;

import com.github.terravivaproject.terraviva.seed.entities.Species;
import com.github.terravivaproject.terraviva.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Varieties {

    @Id
    @NotBlank
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @NotNull
    private String name;


    @OneToOne
    private Species species;

    //TODO implements @Pattern -> URI
    private String variety_image;

    @NotBlank @NotNull
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "varieties_followers",
            joinColumns = { @JoinColumn(name = "variety_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    private List<User> followers;



}
