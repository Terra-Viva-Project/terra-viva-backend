package com.github.terravivaproject.terraviva.social.repositories;


import com.github.terravivaproject.terraviva.social.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TagRepository interface.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * findByName.
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.Optional} object
     */
    public Optional<Tag> findByName(String name);
}
