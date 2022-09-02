package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreationPostDto {

    private List<String> tags;

    @NotNull
    @NotBlank
    private String message;

    @NotNull
    //@UUID
    private UUID owner;

    public CreationPostDto setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public CreationPostDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public CreationPostDto setOwner(UUID owner) {
        this.owner = owner;
        return this;
    }
}
