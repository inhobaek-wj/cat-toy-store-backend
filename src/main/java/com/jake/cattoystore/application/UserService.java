package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.github.dozermapper.core.Mapper;
import com.jake.cattoystore.domain.User;
import com.jake.cattoystore.domain.UserRepository;
import com.jake.cattoystore.dto.SigninRequestDto;
import com.jake.cattoystore.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private Mapper mapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       Mapper mapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    // public User register(User user) {
    public User register(UserDto userDto) {
        this.userRepository.findByEmail(userDto.getEmail())
            .ifPresent((existedUser) -> {throw new EntityExistsException();});

        User user = mapper.map(userDto, User.class);

        user.hashPassword(this.passwordEncoder);
        return this.userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = this.userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException());

        if (!user.matchesPassword(passwordEncoder, password)) {
            return null;
        }

        return user;
    }


}
