package com.github.terravivaproject.terraviva.user.entities;

import com.github.terravivaproject.terraviva.entities.Variety;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
