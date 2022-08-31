package com.github.terravivaproject.terraviva.seed.entities;


import com.github.terravivaproject.terraviva.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @NotBlank
    @NotNull
    private Long id;

    @NotNull
    private String scientific_name;

    @OneToMany(mappedBy = "species")
    private List<Variety> varieties;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "species_followers",
            joinColumns = { @JoinColumn(name = "species_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    private List<User> followers;
}
