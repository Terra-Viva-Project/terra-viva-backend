package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class PostRto {

    private Collection<String> tags;

    @NotNull
    @NotBlank
    private String message;
}