package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.exceptions.UserAlreadyExistsException;
import com.github.terravivaproject.terraviva.user.entities.User;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String USER_NOT_FOUND = "This user [%s] doesn't exist.";
    private final BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.getByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, usernameOrEmail)
                ));
    }

    public UserDto signUpUser(User user) {
        boolean emailAlreadyExist = userRepository.existsUserByEmail(user.getEmail());
        boolean usernameAlreadyExist = userRepository.existsUserByUsername(user.getUsername());

        List<String> errors = new ArrayList<>();
        if (emailAlreadyExist) errors.add("email -> already exists.");
        if (usernameAlreadyExist) errors.add("username -> already exists.");

        if (!errors.isEmpty()) throw new UserAlreadyExistsException(errors);

        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        return UserMapper.MAP.userToUserDto(user);
    }
}
