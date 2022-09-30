package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagRto;
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
     * createTag.
     *
     * @param tagRto a {@link com.github.terravivaproject.terraviva.social.entities.dto.TagRto} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.TagDto} object
     */
    public TagDto createTag(TagRto tagRto) {

        Optional<Tag> optionalTag = tagRepository.findByName(tagRto.getName());
        if (optionalTag.isEmpty()) {
            Tag tag = new Tag();
            tag.setName(tagRto.getName());
            tagRepository.save(tag);
            return new TagDto()
                    .setName(tag.getName())
                    .setId(tag.getId());
        } else {
            return new TagDto()
                    .setName(optionalTag.get().getName())
                    .setId(optionalTag.get().getId());

        }
    }

    /**
     * getAllTags.
     *
     * @return a {@link java.util.List} object
     */
    public List<TagDto> getAllTags() {
        List<Tag> tagList = tagRepository.findAll();

        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagDtoList.add(new TagDto()
                    .setId(tag.getId())
                    .setName(tag.getName())
            );
        }
        return tagDtoList;
    }


    /**
     * getSingleTag.
     *
     * @param id a {@link java.lang.Long} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.TagDto} object
     */
    public TagDto getSingleTag(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isEmpty()) throw new RuntimeException("This tag does not exist");

        return new TagDto()
                .setId(tag.get().getId())
                .setName(tag.get().getName());
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
