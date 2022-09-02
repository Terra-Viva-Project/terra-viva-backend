package com.github.terravivaproject.terraviva.user.repositories;

import com.github.terravivaproject.terraviva.user.entities.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, UUID> {
}
