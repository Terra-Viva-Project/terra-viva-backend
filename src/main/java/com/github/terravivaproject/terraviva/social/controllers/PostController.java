package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.repositories.PostRepository;
import com.github.terravivaproject.terraviva.social.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    private PostRepository postRepository;
}
