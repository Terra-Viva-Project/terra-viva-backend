package com.github.terravivaproject.terraviva.seed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Varieties {

    @NotBlank
    @NotNull
    private Long id;


    @NotBlank
    @NotNull
    private String name;

    private Species species;

    //TODO implements @Pattern -> URI
    private String variety_image;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdOn;


}
