package com.jake.cattoystore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jake.cattoystore.application.GreetingService;

import static org.hamcrest.CoreMatchers.containsString;

// it takes a little bit long to test controller.

@ExtendWith(SpringExtension.class) // junit5 <- @RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
@ActiveProfiles("test")
public class GreetingControllerTest {

    // MockMvc object is injected by Spring IoC Container.
    @Autowired
    private MockMvc mockMvc;

    // when test controller, you need mock object
    @MockBean
    private GreetingService greetingService;

    @BeforeEach
    public void mockGreetingService() {
        given(greetingService.getMessage(null)).willReturn("Hello");

        given(greetingService.getMessage("jake")).willReturn("Hello, jake");
    }

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello")));

        // check if getMessage method is called with verify.
        verify(greetingService).getMessage(null);
    }

    @Test
    public void helloWithName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "jake"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, jake")));

        verify(greetingService).getMessage("jake");
    }
}
