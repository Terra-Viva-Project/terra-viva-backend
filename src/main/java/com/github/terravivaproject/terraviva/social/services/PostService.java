package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.exceptions.UnauthorizedException;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostRto;
import com.github.terravivaproject.terraviva.social.entities.mappers.PostMapper;
import com.github.terravivaproject.terraviva.social.repositories.PostRepository;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.services.UserService;
import dev.dmgiangi.budssecurity.securitycontext.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagService tagService;
    private final UserService userService;

    public Post createPost(PostRto postRequest) {
        //Map a set Of Tag entities from the strings found in PostRto
        Set<Tag> tags = tagService.tagFromStrings(postRequest.getTags());

        //Get the user from the UserService using the Security user from the SecurityContext
        AppUser postOwner = userService.getUserById(
                UUID.fromString(
                        SecurityContext.getUser().getMainIdentifier()
                )
        );

        //create a new post entity
        Post newPost = PostMapper.MAP.rtoToEntity(postRequest, postOwner, tags);

        return postRepository.save(newPost);
    }

    public Post getPostId(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExist(
                        ErrorMessagesService.resourceNotExist()
                ));
    }

    public void deletePost(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExist(
                        ErrorMessagesService.resourceNotExist()
                ));

        UUID authenticatedUserId =
                UUID.fromString(
                        SecurityContext.getUser().getMainIdentifier()
                );

        // TODO: 23/09/22 permits roles check
        if (!post.getOwner().getId().equals(authenticatedUserId))
            throw new UnauthorizedException(
                    ErrorMessagesService.notEnoughAuthorization()
            );

        postRepository.deleteById(id);
    }


    public List<PostDto> getPostsByTag(String tagName, Integer page, Integer size) {
        Optional<Tag> tag = tagService.getTagByName(tagName);
        if(tag.isEmpty()) return Collections.emptyList();
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByTags(tag.get(), pageable);
    }
}
