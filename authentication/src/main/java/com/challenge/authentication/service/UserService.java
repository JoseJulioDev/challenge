package com.challenge.authentication.service;

import com.challenge.authentication.dto.UserDTO;
import com.challenge.authentication.entity.User;
import com.challenge.authentication.mapper.UserMapper;
import com.challenge.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isActive(), true, true, true, new ArrayList<>());
    }

    public User saveUser(UserDTO userDTO) {

        User user = UserMapper.toEntity(userDTO);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

