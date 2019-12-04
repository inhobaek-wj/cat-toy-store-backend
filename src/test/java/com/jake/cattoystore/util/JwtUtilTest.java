package com.jake.cattoystore.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";

    // you should get token before test with SECRET and datas
    // from https://jwt.io
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
        + "eyJ1c2VySWQiOjEzLCJuYW1lIjoidGVzdGVyIn0."
        + "s7-SwQW2LsfmFjEQGQbsJciJB1R7rvoDGvpRXpfFJJA";

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

    @Test
    public void parseToken() {
        Claims claims = jwtUtil.parseToken(TOKEN);

        assertThat(claims.get("userId", Long.class)).isEqualTo(13L);
        assertThat(claims.get("name")).isEqualTo("tester");

    }
}
