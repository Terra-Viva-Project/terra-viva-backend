package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;


    public Set<Tag> tagFromStrings(List<String> tagNames) {
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
}
