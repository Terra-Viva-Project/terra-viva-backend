package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.social.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;
}
