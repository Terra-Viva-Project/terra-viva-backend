package com.github.terravivaproject.terraviva.social.entities.mappers;

import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import org.mapstruct.factory.Mappers;

/**
 * TagMapper interface.
 *
 * @author giangi
 * @version $Id: $Id
 */
public interface TagMapper {
    /**
     * Constant <code>MAP</code>
     */
    TagMapper MAP = Mappers.getMapper(TagMapper.class);

    /**
     * entityToDto.
     *
     * @param tag a {@link com.github.terravivaproject.terraviva.social.entities.Tag} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.TagDto} object
     */
    TagDto entityToDto(Tag tag);

}
