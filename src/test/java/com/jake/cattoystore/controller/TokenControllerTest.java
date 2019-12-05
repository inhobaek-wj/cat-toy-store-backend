package com.jake.cattoystore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.jake.cattoystore.application.UserService;
import com.jake.cattoystore.domain.User;

import static org.hamcrest.CoreMatchers.containsString;

// it takes a little bit long to test controller.

@ExtendWith(SpringExtension.class) // junit5 <- @RunWith(SpringRunner.class)
@WebMvcTest(TokenController.class)
@ActiveProfiles("test")
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        User user = User.builder()
            .email("tester@example.com")
            .name("tester")
            // .password("pass")
            .build();

        given(userService.authenticate("tester@example.com", "pass"))
            .willReturn(user);

        given(userService.authenticate("x@example.com", "x"))
            .willReturn(null);

    }

    @Test
    public void signinWithValidAttributes() throws Exception {

        mockMvc.perform(
                        post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"tester@example.com\","
                                 + "\"password\":\"pass\"}")
                        )
            .andExpect(status().isCreated())
            .andExpect(content().string(containsString("accessToken")))
            .andExpect(content().string(containsString(".")));

        verify(userService).authenticate("tester@example.com","pass");
    }

    @Test
    public void signinWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                        post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"x@example.com\","
                                 + "\"password\":\"x\"}")
                        )
            .andExpect(status().isNotFound()); // this needs EntityNotFoundException

    }

    @Test
    public void signinWithNoPassword() throws Exception {
        mockMvc.perform(
                        post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
            .andExpect(status().isBadRequest());
    }

}
