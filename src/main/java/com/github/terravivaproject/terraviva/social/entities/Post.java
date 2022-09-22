package com.github.terravivaproject.terraviva.social.entities;

import com.github.terravivaproject.terraviva.user.entities.AppUser;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    @NotBlank
    @NotNull
    private String message;

    @CreationTimestamp
    @Setter(value = AccessLevel.PRIVATE)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser owner;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "post_likes",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<AppUser> likes;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE}
    )
    @JoinTable(name = "post_tags",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;

    public Set<AppUser> getLikes() {
        if (likes == null)
            likes = new HashSet<>();
        return likes;
    }

    public Set<Tag> getTags() {
        if (tags == null)
            tags = new HashSet<>();
        return tags;
    }
}