package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.jake.cattoystore.domain.User;
import com.jake.cattoystore.domain.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        user.hashPassword(this.passwordEncoder);
        return this.userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = this.userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException());

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return user;
    }


}
