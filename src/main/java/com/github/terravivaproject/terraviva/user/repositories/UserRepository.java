package com.github.terravivaproject.terraviva.user.repositories;

import com.github.terravivaproject.terraviva.user.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
    @Query("SELECT u FROM AppUser u WHERE u.username = ?1 OR u.email = ?1")
    Optional<AppUser> getByUsernameOrEmail(String usernameOrEmail);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);
}
