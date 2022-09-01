package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PostDto extends CreationPostDto {

    private UUID id;

    private LocalDateTime dateTime;

    public PostDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public PostDto setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    @Override
    public PostDto setTags(List<String> tags) {
        super.setTags(tags);
        return this;
    }

    @Override
    public PostDto setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public PostDto setOwner(UUID owner) {
        super.setOwner(owner);
        return this;
    }
}
