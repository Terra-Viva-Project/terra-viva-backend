package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class CreationTagDto {

    @NotNull
    @NotBlank
    private String name;

    public CreationTagDto setName(String name) {
        this.name = name;
        return this;
    }

}
