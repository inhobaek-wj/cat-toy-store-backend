package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.jake.cattoystore.domain.User;
import com.jake.cattoystore.domain.UserRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return this.userRepository.save(user);
    }


}
