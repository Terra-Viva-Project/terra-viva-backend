package com.github.terravivaproject.terraviva.entities;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createdOn = LocalDateTime.now();

    private User createdBy;
}
