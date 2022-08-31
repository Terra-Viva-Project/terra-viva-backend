package com.github.terravivaproject.terraviva.user.entities;

import com.github.terravivaproject.terraviva.media.entities.Media;
import com.github.terravivaproject.terraviva.seed.entities.Species;
import com.github.terravivaproject.terraviva.seed.entities.Varieties;
import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.seed.entities.Variety;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @NotBlank @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate birthDate;
    @NotBlank @NotNull
    private LocalDateTime subscribedOn = LocalDateTime.now();
    @NotBlank @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private Boolean verified;
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private Boolean locked;
    @Column(length = 1000)
    private String bio;

    private UserRole userRole;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Variety.class)
    private List<Variety> varieties;

    @ManyToMany(mappedBy = "likes", fetch = FetchType.LAZY)
    private List<Post> likedPost;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Media> media;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private List<Tag> followedTags;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private List<Varieties> followedVarieties;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private List<Species> followedSpecies;

    @ManyToMany(mappedBy = "followers")
    private List<User> followedUser;

    @ManyToMany
    @JoinTable(name = "user_followers",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "followers_id")})
    private List<User> followers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
