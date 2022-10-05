package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostRto;
import com.github.terravivaproject.terraviva.social.entities.mappers.PostMapper;
import com.github.terravivaproject.terraviva.social.services.PostService;
import com.github.terravivaproject.terraviva.user.services.UserService;
import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
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
@Tag(name = "Post", description = "Post Related Endpoint")
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
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "create a post",
            description = "Create a post, the user information are taken from authentication header",
            security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Post is created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PostDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The authenticated user doesn't exist",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)))
            })
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
    @GetMapping(value = "/{id}")
    @Operation(
            summary = "get a post",
            description = "get information about a single post by UUID of the post",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Post information",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PostDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Post does not exists",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public PostDto getSinglePost(@PathVariable @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") UUID id) {
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
    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "delete a post",
            description = "delete a post, the user information are taken from authentication header",
            security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "post successfully deleted",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "the request have to low Authorization",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public ResponseEntity<Void> deleteSinglePost(
            @PathVariable @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") UUID id) {
        postService.deletePost(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
