package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.jake.cattoystore.application.GreetingService;

public class GreetingServiceTest {


    // for multiple tests, set product as a member.
    private GreetingService greetingService;

    @BeforeEach
    public void setUp() {
        greetingService = new GreetingService();
    }

    @Test
    public void getMessage() {
        assertThat(greetingService.getMessage(null)).isEqualTo("Hello");
    }

    @Test
    public void getMessageWithName() {
        assertThat(greetingService.getMessage("jake"))
            .isEqualTo("Hello, jake");
    }
}
