package com.jake.cattoystore.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;

public class UserAuthentication extends AbstractAuthenticationToken {

    private Claims claims;

    public UserAuthentication(Claims claims) {
        // super(null);
        super(authorities(claims));
        this.claims = claims;
    }

    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getPrincipal() {
        // authenticated user info.
        // if this is null, it will not be passed to the controller.
        return claims;
    }

    @Override
    public String getName() {
        return claims.get("name", String.class);
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    private static List<GrantedAuthority> authorities(Claims claims) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // if userId == 1, give him ADMIN authorization.
        Long userId = claims.get("userId", Long.class);
        if (userId == 1L) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return authorities;
    }
}
