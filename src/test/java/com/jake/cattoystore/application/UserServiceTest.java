package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jake.cattoystore.domain.User;
import com.jake.cattoystore.domain.UserRepository;

public class UserServiceTest {


    // for multiple tests, set product as a member.
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();

        userService = new UserService(userRepository,
                                      passwordEncoder);
    }

    @Test
    public void register() {
        User user = User.builder()
            .name("tester")
            .email("tester@example.com")
            .password("pass")
            .build();

        userService.register(user);

        assertThat(user.getPassword()).isNotEqualTo("pass");

        verify(userRepository).save(user);
    }
}
