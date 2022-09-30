package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.resources.validations.UuidValidation;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostRto;
import com.github.terravivaproject.terraviva.social.entities.mappers.PostMapper;
import com.github.terravivaproject.terraviva.social.services.PostService;
import com.github.terravivaproject.terraviva.user.services.UserService;
import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

/**
 * PostController class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;

    /**
     * createPost.
     *
     * @param postRequest a {@link com.github.terravivaproject.terraviva.social.entities.dto.PostRto} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostRto postRequest) {
        //We Ask the postService to persist the new post
        PostDto post = postService.createPost(postRequest);

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
                .body(post);
    }

    /**
     * getSinglePost.
     *
     * @param id a {@link java.util.UUID} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.PostDto} object
     */
    @Public
    @GetMapping("/{id}")
    public PostDto getSinglePost(@Valid @UuidValidation @PathVariable UUID id) {
        return PostMapper.MAP.entityToDto(
                postService.getPostById(id)
        );
    }

    /**
     * deleteSinglePost.
     *
     * @param id a {@link java.util.UUID} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinglePost(@Valid @UuidValidation @PathVariable UUID id) {
        postService.deletePost(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
