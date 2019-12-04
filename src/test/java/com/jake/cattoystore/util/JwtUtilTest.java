package com.jake.cattoystore.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";


    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {

        String token = jwtUtil.createToken(13L, "tester");

        assertThat(token).isNotBlank();
    }

}
