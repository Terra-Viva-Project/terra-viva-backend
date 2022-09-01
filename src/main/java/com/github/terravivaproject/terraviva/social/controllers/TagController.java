package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.repositories.TagRepository;
import com.github.terravivaproject.terraviva.social.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    private TagRepository tagRepository;

}
