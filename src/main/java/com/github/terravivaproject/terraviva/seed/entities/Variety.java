package com.github.terravivaproject.terraviva.seed.entities;

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
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Variety {
    @Id
    @NotBlank
    @NotNull
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @ManyToOne
    private Species species;

    //TODO implements @Pattern -> URI
    private String variety_image;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createDataTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "varieties_followers",
            joinColumns = {@JoinColumn(name = "variety_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> followers;
}
