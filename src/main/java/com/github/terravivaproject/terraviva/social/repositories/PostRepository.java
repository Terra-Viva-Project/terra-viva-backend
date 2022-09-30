package com.github.terravivaproject.terraviva.social.repositories;

import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * PostRepository interface.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    /**
     * findByTags.
     *
     * @param tag      a {@link com.github.terravivaproject.terraviva.social.entities.Tag} object
     * @param pageable a {@link org.springframework.data.domain.Pageable} object
     * @return a {@link java.util.List} object
     */
    List<Post> findByTags(Tag tag, Pageable pageable);
}
