package com.jake.cattoystore.util;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";

    @Test
    public void createToken() {
        JwtUtil jwtUtil = new JwtUtil(SECRET);

        String token = jwtUtil.createToken(13L, "tester");

        assertThat(token).isNotBlank();
    }

}
