package com.github.terravivaproject.terraviva.social.entities;

import com.github.terravivaproject.terraviva.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
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
    private Set<User> followers;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> relatedPost;

    public Set<User> getFollowers() {
        if (followers == null)
            followers = new HashSet<>();
        return followers;
    }

    public Set<Post> getRelatedPost() {
        if (relatedPost == null)
            relatedPost = new HashSet<>();
        return relatedPost;
    }
}

