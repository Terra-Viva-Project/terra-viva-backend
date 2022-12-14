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

/**
 * Post class.
 *
 * @author giangi
 * @version $Id: $Id
 */
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
    @Column(length = 1000)
    private String message;

    @CreationTimestamp
    @Setter(value = AccessLevel.PRIVATE)
    private LocalDateTime creationDateTime;

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

    /**
     * Getter for the field <code>likes</code>.
     *
     * @return a {@link java.util.Set} object
     */
    public Set<AppUser> getLikes() {
        if (likes == null)
            likes = new HashSet<>();
        return likes;
    }

    /**
     * Getter for the field <code>tags</code>.
     *
     * @return a {@link java.util.Set} object
     */
    public Set<Tag> getTags() {
        if (tags == null)
            tags = new HashSet<>();
        return tags;
    }
}
