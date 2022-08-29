package com.github.terravivaproject.terraviva.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long follower_id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long followed_id;
    @Column(nullable = false)
    private LocalDateTime follow_datetime;

    @JoinColumn
    @OneToMany(mappedBy = User)
    private List<User> user_followers;
}
