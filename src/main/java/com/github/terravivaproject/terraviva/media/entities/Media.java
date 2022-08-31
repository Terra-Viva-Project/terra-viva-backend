package com.github.terravivaproject.terraviva.media.entities;

import com.github.terravivaproject.terraviva.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdOn;

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
