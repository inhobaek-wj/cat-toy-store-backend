package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);
    }

    @Test
    public void register() {
        User user = User.builder()
            .name("tester")
            .email("tester@example.com")
            .password("pass")
            .build();

        userService.register(user);

        verify(userRepository).save(user);
    }
}
