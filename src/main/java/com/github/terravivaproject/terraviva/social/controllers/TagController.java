package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagRto;
import com.github.terravivaproject.terraviva.social.services.PostService;
import com.github.terravivaproject.terraviva.social.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    private final PostService postService;


    //
    @GetMapping("/{tagName}")
    public List<PostDto> tagList(
            @PathVariable String tagName,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        if(page < 0) page = 0;
        if(size < 0 || size > 20) size = 10;


        return postService.getPostsByTag(tagName, page, size);
    }

    @GetMapping("/{id}")
    public TagDto getSiglTag(@PathVariable Long id) {

        return tagService.getSingleTag(id);
    }


}
