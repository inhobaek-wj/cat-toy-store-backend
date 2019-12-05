package com.jake.cattoystore.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import io.jsonwebtoken.Claims;

public class UserAuthentication extends AbstractAuthenticationToken {

    private Claims claims;

    public UserAuthentication(Claims claims) {
        super(null);
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
}
