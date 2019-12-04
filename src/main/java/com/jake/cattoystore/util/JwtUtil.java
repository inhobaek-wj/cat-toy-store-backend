package com.jake.cattoystore.util;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private Key key;

    public JwtUtil(String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String name) {

        String token = Jwts.builder()
            .claim("userId", userId)
            .claim("name", name)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

        return token;
    }

    public Claims parseToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();

        return claims;
    }
}
