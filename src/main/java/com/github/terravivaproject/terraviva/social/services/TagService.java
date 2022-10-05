package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.entities.mappers.TagMapper;
import com.github.terravivaproject.terraviva.social.repositories.TagRepository;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.dto.MinimalUserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * TagService class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;
    private UserService userService;

    /**
     * tagFromStrings.
     *
     * @param tagNames a {@link java.util.Collection} object
     * @return a {@link java.util.Set} object
     */
    public Set<Tag> tagFromStrings(Collection<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) return null;

        Set<Tag> tags = new HashSet<>();
        for (String tagName : tagNames) {
            Optional<Tag> tag = tagRepository.findByName(tagName);
            if (tag.isEmpty()) {
                Tag createdTagThatDoesNotExist = tagRepository.save(new Tag().setName(tagName));
                tags.add(createdTagThatDoesNotExist);
            } else tags.add(tag.get());
        }

        return tags;
    }

    /**
     * getTagByName.
     *
     * @param tagName a {@link java.lang.String} object
     * @return a {@link java.util.Optional} object
     */
    public Optional<Tag> getTagByName(String tagName) {
        return tagRepository.findByName(tagName);
    }

    /**
     * getPagedResetUpdatedTag.
     *
     * @param size a {@link java.lang.Integer} object
     * @param page a {@link java.lang.Integer} object
     * @return a {@link org.springframework.data.domain.Page} object
     */
    public Page<TagDto> getPagedRecentUpdatedTag(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedOn"));
        Page<Tag> tagsPage = tagRepository.findAll(pageable);
        return tagsPage
                .map(TagMapper.MAP::entityToDto);

    }

    public void followTag(String tagName) {
        Tag tag = this.getTagByName(tagName)
                .orElseThrow(() -> new EntityDoesNotExist("this tag does not exist")
                );

        AppUser authenticatedUser = userService.getAuthenticatedUser();

        tag.getFollowers().add(authenticatedUser);
        tagRepository.save(tag);
    }

    public Page<MinimalUserDto> getTagFollower(String tagName, Integer page, Integer size) {
        Tag tag = this.getTagByName(tagName)
                .orElseThrow(() -> new EntityDoesNotExist("this tag does not exist")
                );

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedOn"));

        List<MinimalUserDto> tagFollower = tag
                .getFollowers()
                .stream()
                .map(
                        UserMapper.MAP::entityToMinimalDto)
                .sorted(Comparator.comparing(MinimalUserDto::getUsername))
                .toList();

        return new PageImpl<>(tagFollower, pageable, tagFollower.size());
    }

    public void unfollowTag(String tagName) {
        Tag tag = this.getTagByName(tagName)
                .orElseThrow(() -> new EntityDoesNotExist("this tag does not exist")
                );

        AppUser authenticatedUser = userService.getAuthenticatedUser();

        tag.getFollowers().remove(authenticatedUser);
        tagRepository.save(tag);
    }
}
