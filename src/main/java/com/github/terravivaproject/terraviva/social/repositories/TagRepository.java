package com.github.terravivaproject.terraviva.social.repositories;


import com.github.terravivaproject.terraviva.social.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    public Tag findByName(String name);
}
