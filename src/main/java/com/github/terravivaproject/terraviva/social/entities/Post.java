package com.github.terravivaproject.terraviva.social.entities;

import com.github.terravivaproject.terraviva.user.entities.User;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @NotNull
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_likes",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> likes;

    @ManyToMany(mappedBy = "relatedPost", cascade = CascadeType.MERGE)
    private List<Tag> tags;

    @NotNull
    private String message;

    @CreationTimestamp
    @Setter(value = AccessLevel.PRIVATE)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}