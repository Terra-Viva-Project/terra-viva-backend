package com.github.terravivaproject.terraviva.user.entities;

import com.github.terravivaproject.terraviva.media.entities.Media;
import com.github.terravivaproject.terraviva.seed.entities.Species;
import com.github.terravivaproject.terraviva.seed.entities.Variety;
import com.github.terravivaproject.terraviva.social.entities.Post;
import com.github.terravivaproject.terraviva.social.entities.Tag;
import com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
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
import java.util.Set;
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
    @NotNull
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDateTime subscribedOn;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 60)
    private String password;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private Boolean verified = false;

    @NotNull
    @Column(nullable = false)
    private Boolean locked = false;

    @Column(length = 1000)
    private String bio;

    @NotNull
    @Column(nullable = false)
    private UserRole userRole = UserRole.USER;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Post> ownedPost;

    @ManyToMany(mappedBy = "likes", fetch = FetchType.LAZY)
    private Set<Post> likedPost;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Media> media;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private Set<Tag> followedTags;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private Set<Variety> followedVarieties;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private Set<Species> followedSpecies;

    @ManyToMany(mappedBy = "followers")
    private Set<User> followedUser;

    @ManyToMany
    @JoinTable(name = "user_followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "followers_id")})
    private List<User> followers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.verified;
    }
}
