package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;


    public List<Tag> tagFromStrings(List<String> tagNames) {

        if(tagNames == null || tagNames.isEmpty()) return null;

        List<Tag> tags = new ArrayList<>();

        for (String tagName: tagNames) {

            Optional<Tag> tag = tagRepository.findByName(tagName);

            if(tag.isEmpty()) {

                Tag createdTagThatdoesntExist = tagRepository.save(new Tag().setName(tagName));

                tags.add(createdTagThatdoesntExist);

            } else tags.add(tag.get());
        }
        return tags;
    }
}
