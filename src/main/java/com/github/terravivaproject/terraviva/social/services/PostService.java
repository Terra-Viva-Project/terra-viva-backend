package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.social.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
}
