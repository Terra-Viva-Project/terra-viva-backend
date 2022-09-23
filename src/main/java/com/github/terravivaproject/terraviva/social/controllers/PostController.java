package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostRto;
import com.github.terravivaproject.terraviva.social.entities.mappers.PostMapper;
import com.github.terravivaproject.terraviva.social.services.PostService;
import dev.dmgiangi.budssecurity.authorizzation.annotations.Public;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostRto postRequest) {
        //We Ask the postService to persist the new post
        Post post = postService.createPost(postRequest);

        //Return a 201 response with the user data and the uri of the resource
        return ResponseEntity
                .created(
                        URI.create(
                                ServletUriComponentsBuilder
                                        .fromCurrentContextPath()
                                        .path("posts/" + post.getId().toString())
                                        .toUriString()
                        )
                )
                .body(PostMapper.MAP.entityToDto(post));
    }

    @Public
    @GetMapping("/{id}")
    // TODO: 23/09/22 implements @UUID validation annotation
    public PostDto getSinglePost(@PathVariable UUID id) {
        return PostMapper.MAP.entityToDto(
                postService.getPostId(id)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteSinglePost(@PathVariable UUID id) {

        postService.deletePost(id);
    }
}
