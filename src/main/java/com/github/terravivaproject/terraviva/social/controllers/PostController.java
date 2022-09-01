package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.dto.CreationPostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.repositories.PostRepository;
import com.github.terravivaproject.terraviva.social.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;


    @PostMapping
    public PostDto createPost(@Valid @RequestBody CreationPostDto post){
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public PostDto getSinglePost(@PathVariable UUID id) {
        return postService.getPostId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSinglePost(@PathVariable UUID id){

        postService.deletePost(id);
    }
}
