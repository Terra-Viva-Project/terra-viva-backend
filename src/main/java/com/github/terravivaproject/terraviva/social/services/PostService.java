package com.github.terravivaproject.terraviva.social.services;

import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.social.entities.dto.CreationPostDto;
import com.github.terravivaproject.terraviva.social.entities.dto.PostDto;
import com.github.terravivaproject.terraviva.social.repositories.PostRepository;
import com.github.terravivaproject.terraviva.user.entities.User;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private TagService tagService;

    public PostDto createPost(CreationPostDto creationPostDto) {
        Optional<User> owner = userRepository.findById(creationPostDto.getOwner());
        if (owner.isEmpty()) throw new RuntimeException("The user does not exist");
        Set<Tag> tags = tagService.tagFromStrings(creationPostDto.getTags());

        Post post = new Post();
        post.setTags(tags)
                .setOwner(owner.get())
                .setMessage(creationPostDto.getMessage());

        postRepository.save(post);

        return new PostDto()
                .setId(post.getId())
                .setMessage(post.getMessage())
                .setOwner(post.getOwner().getId())
                .setTags(post.getTags().stream().map(Tag::getName).toList())
                .setDateTime(post.getDateTime());
    }

    public PostDto getPostId(UUID id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) throw new RuntimeException("This post does not exist");
        Set<Tag> tags = post.get().getTags();
        return new PostDto()
                .setDateTime(post.get().getDateTime())
                .setMessage(post.get().getMessage())
                .setId(post.get().getId())
                .setOwner(post.get().getOwner().getId())
                .setTags(tags.stream().map(Tag::getName).toList());
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }
}
