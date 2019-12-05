package com.jake.cattoystore;

import javax.servlet.Filter;

import com.jake.cattoystore.authentication.JwtAuthenticationFilter;
import com.jake.cattoystore.util.JwtUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;


    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // filter for user authentication.
        Filter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(),
                                                                     jwtUtil());

        http.cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .addFilter(jwtAuthenticationFilter)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            // if authentication error occur, 401 Unauthorized error.
            .authenticationEntryPoint(
                                      new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                                      );
    }

}
