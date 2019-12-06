package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.jake.cattoystore.domain.User;
import com.jake.cattoystore.domain.UserRepository;

public class UserServiceTest {


    // for multiple tests, set product as a member.
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    // JUnit Jupiter does not guarantee the execution order of multiple @BeforeEach methods that are declared within a single test class or test interface.
    // While it may at times appear that these methods are invoked in alphabetical order, they are in fact sorted using an algorithm that is deterministic but intentionally non-obvious.
    @BeforeEach
    public void aSetUp() {
        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();

        userService = new UserService(userRepository,
                                      passwordEncoder);
    }

    @BeforeEach
    public void mockUserRepository() {
        User mockUser = User.builder()
            .name("tester")
            .email("tester@example.com")
            .password(passwordEncoder.encode("pass"))
            .build();

        // this code not work. I tried to use any()...
        // given(userRepository.save(any())).willReturn(Optional.of(mockUser));

        given(userRepository.findByEmail("tester@example.com"))
            .willReturn(Optional.of(mockUser));

    }

    @Test
    public void register() {
        //Given.
        User user = User.builder()
            .name("newUser")
            .email("newUser@example.com")
            .password("pass")
            .build();

        // When.
        userService.register(user);
        // User user = userService.register(any());         // this code not work.

        // Then.
        // assertThat(user.getPassword()).isNotEqualTo("pass"); // remove @Getter of password.
        assertThat(user.matchesPassword(passwordEncoder, "pass")).isEqualTo(true);

        verify(userRepository).save(user);
    }

    @Test
    public void registerWithDuplicatedEmail() {
        //Given.
        User user = User.builder()
            .name("tester")
            .email("tester@example.com")
            .password("pass")
            .build();

        // use mockUser at BeforeEach.

        // Then.
        assertThrows(EntityExistsException.class,
                     () -> userService.register(user));

    }

    @Test
    public void authenticateWithValidAttributes() {

        User user = userService.authenticate("tester@example.com", "pass");

        assertThat(user).isNotNull();

    }

    // @Test(expected = EntityNotFoundException.class) <- junit 4.
    @Test
    public void authenticateWithNotExistedEmail() {
        // this is junit 4.
        // given(userRepository.findByEmail("x@example.com"))
        //     .willThrow(new EntityNotFoundException());

        // this is junit 5.
        assertThrows(EntityNotFoundException.class,
                     () -> userService.authenticate("x@example.com", "x"));
    }

    @Test
    public void authenticateWithWrongPassword() {
        User user = userService.authenticate("tester@example.com", "x");

        assertThat(user).isNull();
    }

}
