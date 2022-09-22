package com.github.terravivaproject.terraviva.social.controllers;

import com.github.terravivaproject.terraviva.social.entities.dto.CreationTagDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private TagService tagService;


    @PostMapping("")
    public TagDto tagCreate(@Valid @RequestBody CreationTagDto nameTag) {

        return tagService.createTag(nameTag);
    }


    //reads a tags
    @GetMapping
    public List<TagDto> tagList() {
        return tagService.getAllTags();
    }
    
    @GetMapping("/{id}")
    public TagDto getSiglTag(@PathVariable Long id) {
        return tagService.getSingleTag(id);
    }


}
