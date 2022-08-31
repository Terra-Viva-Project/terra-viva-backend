package com.github.terravivaproject.terraviva.user.repositories;

import com.github.terravivaproject.terraviva.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);
}
