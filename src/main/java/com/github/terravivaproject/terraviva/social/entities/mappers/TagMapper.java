package com.github.terravivaproject.terraviva.social.entities.mappers;

import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import org.mapstruct.factory.Mappers;

public interface TagMapper {
    TagMapper MAP = Mappers.getMapper(TagMapper.class);

    TagDto entityToDto(Tag tag);

}
