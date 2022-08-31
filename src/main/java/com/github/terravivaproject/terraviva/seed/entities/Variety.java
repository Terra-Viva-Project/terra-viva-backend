package com.github.terravivaproject.terraviva.seed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Variety {
    @NotBlank
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    //TODO add index on name
    private String name;
}
