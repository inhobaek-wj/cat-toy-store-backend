package com.jake.cattoystore.util;

import java.security.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private String secret;

    public JwtUtil(String secret) {
        this.secret = secret;
    }

    public String createToken(Long userId, String name) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
            .claim("userId", userId)
            .claim("name", name)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

        return token;
    }
}
