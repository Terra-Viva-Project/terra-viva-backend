package com.github.terravivaproject.terraviva.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.jetty.server.Authentication;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String tag;

    @JoinTable(name = "tag_followers")
    @JoinColumn
    @ManyToMany (mappedBy = User)
    private List<User> tag_followers;

    @JoinTable(name = "rt_posts_tags")
    @JoinColumn
    @ManyToMany(mappedBy = Post)
    private List<Post> rt_posts_tags;

}
