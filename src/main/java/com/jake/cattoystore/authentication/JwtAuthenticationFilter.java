package com.jake.cattoystore.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jake.cattoystore.util.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
        throws IOException, ServletException {

        // split authentication process into another method.(getAuthentication)
        Authentication authentication = getAuthentication(request);

        // if succeed to authentication...
        if (authentication != null) {
            // put authentication object into security context.
            // then we can use authentication object in the controller.
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        // and then excute filter.
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {

        // check if there is token info in header.
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }

        // extract token and get data.
        String token = authorization.substring("Bearer ".length());
        Claims claims = jwtUtil.parseToken(token);

        // create authentication object.
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);

        return authentication;

    }

}
