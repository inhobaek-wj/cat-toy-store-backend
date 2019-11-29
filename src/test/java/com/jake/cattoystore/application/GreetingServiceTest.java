package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingServiceTest {


    // for multiple tests, set product as a member.
    private GreetingService greetingService;

    @BeforeEach
    public void setUp() {
        greetingService = new GreetingService();
    }

    @Test
    public void getMessage() {
        assertThat(greetingService.getMessage()).isEqualTo("Hello");
    }
}
