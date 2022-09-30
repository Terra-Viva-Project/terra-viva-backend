package com.github.terravivaproject.terraviva.user.repositories;

import com.github.terravivaproject.terraviva.user.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository interface.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
    /**
     * getByUsernameOrEmail.
     *
     * @param usernameOrEmail a {@link java.lang.String} object
     * @return a {@link java.util.Optional} object
     */
    @Query("SELECT u FROM AppUser u WHERE u.username = ?1 OR u.email = ?1")
    Optional<AppUser> getByUsernameOrEmail(String usernameOrEmail);

    /**
     * findByUsername.
     *
     * @param username a {@link java.lang.String} object
     * @return a {@link java.util.Optional} object
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * findByEmail.
     *
     * @param email a {@link java.lang.String} object
     * @return a {@link java.util.Optional} object
     */
    Optional<AppUser> findByEmail(String email);

    /**
     * existsUserByUsername.
     *
     * @param username a {@link java.lang.String} object
     * @return a boolean
     */
    boolean existsUserByUsername(String username);

    /**
     * existsUserByEmail.
     *
     * @param email a {@link java.lang.String} object
     * @return a boolean
     */
    boolean existsUserByEmail(String email);
}
