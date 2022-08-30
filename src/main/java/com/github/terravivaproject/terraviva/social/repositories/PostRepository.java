package com.github.terravivaproject.terraviva.social.repositories;

import com.github.terravivaproject.terraviva.social.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
