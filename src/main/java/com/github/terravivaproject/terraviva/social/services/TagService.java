package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagRto;
import com.github.terravivaproject.terraviva.social.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;


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


    public TagDto getSingleTag(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isEmpty()) throw new RuntimeException("This tag does not exist");

        return new TagDto()
                .setId(tag.get().getId())
                .setName(tag.get().getName());
    }

}
