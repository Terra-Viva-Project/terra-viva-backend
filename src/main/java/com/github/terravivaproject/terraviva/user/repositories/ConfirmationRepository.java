package com.github.terravivaproject.terraviva.user.repositories;

import com.github.terravivaproject.terraviva.user.entities.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ConfirmationRepository interface.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, UUID> {
}
