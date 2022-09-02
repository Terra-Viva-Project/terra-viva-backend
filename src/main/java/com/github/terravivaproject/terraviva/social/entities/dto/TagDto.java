package com.github.terravivaproject.terraviva.social.entities.dto;

import com.github.terravivaproject.terraviva.social.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
public class TagDto extends CreationTagDto {


    private long id;

    public TagDto setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public TagDto setName(String name) {
        super.setName(name);
        return this;
    }

}
