package com.jake.cattoystore.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingTest {

    @Test
    public void getMessage() {
        Greeting greeting = Greeting.builder().build();
        assertThat(greeting.getMessage()).isEqualTo("Hello");
    }

    @Test
    public void getMessageWithName() {
        Greeting greeting = Greeting.builder().name("jake").build();
        assertThat(greeting.getMessage()).isEqualTo("Hello, jake");
    }

}
