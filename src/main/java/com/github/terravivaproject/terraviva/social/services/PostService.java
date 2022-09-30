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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * PostService class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagService tagService;
    private final UserService userService;

    /**
     * Create a new post from a given PostRto
     *
     * @param postRequest a PostRto Object
     * @return a Post Entity
     */
    public PostDto createPost(PostRto postRequest) {
        AppUser owner = userService.getAuthenticatedUser();

        //Map a set Of Tag entities from the strings found in PostRto
        Set<Tag> tags = tagService.tagFromStrings(postRequest.getTags());

        //create a new post entity
        Post newPost = PostMapper.MAP.rtoToEntity(postRequest, owner, tags);

        //Persist new Post
        return PostMapper.MAP.entityToDto(
                postRepository.save(newPost)
        );
    }

    /**
     * getPostById.
     *
     * @param id a {@link java.util.UUID} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.Post} object
     */
    public Post getPostById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExist(
                        ErrorMessagesService.resourceNotExist()
                ));
    }

    /**
     * deletePost.
     *
     * @param postId a {@link java.util.UUID} object
     */
    public void deletePost(UUID postId) {
        AppUser applicant = userService.getAuthenticatedUser();

        Post post = getPostById(postId);

        if (!post.getOwner().getId().equals(applicant.getId()))
            throw new UnauthorizedException(
                    ErrorMessagesService.notEnoughAuthorization()
            );

        postRepository.deleteById(postId);
    }


    /**
     * getPostsByTag.
     *
     * @param tagName a {@link java.lang.String} object
     * @param page    a {@link java.lang.Integer} object
     * @param size    a {@link java.lang.Integer} object
     * @return a {@link java.util.List} object
     */
    public Page<PostDto> getPostsByTag(String tagName, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Optional<Tag> tag = tagService.getTagByName(tagName);

        return tag
                .map(t -> postRepository.findByTags(t, pageable))
                .orElse(Page.empty())
                .map(PostMapper.MAP::entityToDto);
    }
}
