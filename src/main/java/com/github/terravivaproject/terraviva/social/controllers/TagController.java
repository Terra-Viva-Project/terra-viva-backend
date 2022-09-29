package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagRto;
import com.github.terravivaproject.terraviva.social.services.PostService;
import com.github.terravivaproject.terraviva.social.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    private final PostService postService;


    /**
     * Return a pageable list of Post with the selected tag
     * @param tagName is the name of the tag
     * @param page is the request page number
     * @param size number of the element in the page
     * @return
     */
    @GetMapping("/{tagName}")
    public List<PostDto> tagList(
            @PathVariable String tagName,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        //if the user indicate a negative number of page we set it to 0
        if(page < 0) page = 0;
        //if the user indicate a negative size or a size over 20 we set it to 10
        if(size < 0 || size > 20) size = 10;

        return postService.getPostsByTag(tagName, page, size);
    }

    /**
     * Return all the tags ordered by the last update
     * @param page is the request page number
     * @param size number of the element in the page
     * @return
     */
    @GetMapping
    public Page<TagDto> getUpdatedTags(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        //if the user indicate a negative number of page we set it to 0
        if(page < 0) page = 0;
        //if the user indicate a negative size or a size over 20 we set it to 10
        if(size < 0 || size > 20) size = 10;

        return tagService.getPagedResetUpdatedTag(size, page);
    }


}
