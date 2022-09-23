package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TagDto extends TagRto {


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
