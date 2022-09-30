package com.github.terravivaproject.terraviva.social.entities.mappers;

import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostRto;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This Class provide a mapper for Post entity and Post Dto's
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 23 09 2022
 */
@Mapper
public interface PostMapper {
    /**
     * Constant <code>MAP</code>
     */
    PostMapper MAP = Mappers.getMapper(PostMapper.class);

    /**
     * rtoToEntity.
     *
     * @param rto   a {@link com.github.terravivaproject.terraviva.social.entities.dto.PostRto} object
     * @param owner a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     * @param tags  a {@link java.util.Collection} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.Post} object
     */
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "tags", source = "tags")
    Post rtoToEntity(PostRto rto, AppUser owner, Collection<Tag> tags);


    /**
     * entityToDto.
     *
     * @param post a {@link com.github.terravivaproject.terraviva.social.entities.Post} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.PostDto} object
     */
    PostDto entityToDto(Post post);

    /**
     * map.
     *
     * @param tags a {@link java.util.Collection} object
     * @return a {@link java.util.Collection} object
     */
    default Collection<String> map(Collection<Tag> tags) {
        return tags.stream().map(Tag::getName).collect(Collectors.toSet());
    }

    /**
     * map.
     *
     * @param user a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     * @return a {@link java.util.UUID} object
     */
    default UUID map(AppUser user) {
        return user.getId();
    }
}
