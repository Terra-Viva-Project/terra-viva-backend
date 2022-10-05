package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.services.PostService;
import com.github.terravivaproject.terraviva.social.services.TagService;
import com.github.terravivaproject.terraviva.user.entities.dto.MinimalUserDto;
import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TagController class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/tags")
@AllArgsConstructor
@Tag(name = "Tag", description = "Tag Related Endpoint")
public class TagController {

    private final TagService tagService;

    private final PostService postService;


    /**
     * Return a pageable list of Post with the selected tag
     *
     * @param tagName is the name of the tag
     * @param page    is the request page number
     * @param size    number of the element in the page
     * @return a {@link java.util.List} object
     */
    @Public
    @GetMapping(value = "/{tagName}")
    @Operation(
            tags = {"Post"},
            summary = "Recent tag related post",
            description = "return a paged list of post that are related to a tag",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Page is returned",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            arraySchema = @Schema(implementation = Page.class),
                                            schema = @Schema(implementation = PostDto.class))))
            })
    public Page<PostDto> lastUpdatedPostPerTag(
            @PathVariable @Schema(example = "raccolta") String tagName,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        //if the user indicate a negative number of page we set it to 0
        if (page < 0) page = 0;
        //if the user indicate a negative size or a size over 20 we set it to 10
        if (size < 0 || size > 20) size = 10;

        return postService.getPostsByTag(tagName, page, size);
    }

    /**
     * Return all the tags ordered by the last update
     *
     * @param page is the request page number
     * @param size number of the element in the page
     * @return a {@link org.springframework.data.domain.Page} object
     */

    @Public
    @GetMapping
    @Operation(
            tags = {"Post"},
            summary = "Recent updated tag",
            description = "return a paged list of tag ordered by recent updated first",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Page is returned",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            arraySchema = @Schema(implementation = Page.class),
                                            schema = @Schema(implementation = TagDto.class))))
            })
    public Page<TagDto> getUpdatedTags(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        //if the user indicate a negative number of page we set it to 0
        if (page < 0) page = 0;
        //if the user indicate a negative size or a size over 20 we set it to 10
        if (size < 0 || size > 20) size = 10;

        return tagService.getPagedRecentUpdatedTag(size, page);
    }

    @PutMapping(value = "follow/{tagName}")
    @Operation(
            summary = "follow a tag",
            description = "create a relation beetween the authenticated user and the tag",
            security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The tag is followed",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No tag found with this name",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public ResponseEntity<Void> followTag(
            @PathVariable @Schema(example = "raccolta") String tagName) {
        tagService.followTag(tagName);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("unfollow/{tagName}")
    @Operation(
            summary = "follow a tag",
            description = "create a relation between the authenticated user and the tag",
            security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The tag is followed",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No tag found with this name",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public ResponseEntity<Void> unfollowTag(
            @PathVariable @Schema(example = "pomodori") String tagName) {
        tagService.unfollowTag(tagName);

        return ResponseEntity.ok(null);
    }

    @Public
    @GetMapping("follower/{tagName}")
    @Operation(
            summary = "get the follower of a tag",
            description = "get the follower of a tag",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The tag is followed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            arraySchema = @Schema(implementation = Page.class),
                                            schema = @Schema(implementation = MinimalUserDto.class)))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No tag found with this name",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public Page<MinimalUserDto> getTagFollowers(
            @PathVariable @Schema(example = "pomodori") String tagName,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        //if the user indicate a negative number of page we set it to 0
        if (page < 0) page = 0;
        //if the user indicate a negative size or a size over 20 we set it to 10
        if (size < 0 || size > 20) size = 10;

        return tagService.getTagFollower(tagName, page, size);
    }
}
