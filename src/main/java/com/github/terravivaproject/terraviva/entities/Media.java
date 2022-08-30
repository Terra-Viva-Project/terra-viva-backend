package com.github.terravivaproject.terraviva.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @NotBlank
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @NotNull
    private LocalDateTime createdOn = LocalDateTime.now();

    @NotBlank
    @NotNull
    @Column(length = 50)
    private String location;

    @NotBlank
    @NotNull
    @Column(length = 32)
    private String mediaMd5;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

}
