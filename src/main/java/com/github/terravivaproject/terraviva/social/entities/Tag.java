package com.github.terravivaproject.terraviva.social.entities;

import com.github.terravivaproject.terraviva.user.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Tag class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Entity
@Table
@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE}
    )
    @JoinTable(name = "tag_followers",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<AppUser> followers;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> relatedPost;

    /**
     * Getter for the field <code>followers</code>.
     *
     * @return a {@link java.util.Set} object
     */
    public Set<AppUser> getFollowers() {
        if (followers == null)
            followers = new HashSet<>();
        return followers;
    }

    /**
     * Getter for the field <code>relatedPost</code>.
     *
     * @return a {@link java.util.Set} object
     */
    public Set<Post> getRelatedPost() {
        if (relatedPost == null)
            relatedPost = new HashSet<>();
        return relatedPost;
    }

    @CreationTimestamp
    private LocalDateTime subscribedOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;
}

