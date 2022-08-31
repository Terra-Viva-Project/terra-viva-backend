package com.github.terravivaproject.terraviva.seed.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    private List<Varieties> varietiesList;

    public Species setId(Long id) {
        this.id = id;
        return this;
    }

}
