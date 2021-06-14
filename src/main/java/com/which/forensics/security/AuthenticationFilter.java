package com.which.forensics.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.which.forensics.domain.UserDetailsRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDetailsRequest userDetailsRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserDetailsRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetailsRequest.getEmailId(),
                    userDetailsRequest.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth) throws IOException, ServletException {
        String username = ((User) auth.getPrincipal()).getUsername();
        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.getTokenSecret())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_DURATION))
                .compact();
        response.addHeader(SecurityConstant.HEADER_AUTHORIZATION, SecurityConstant.TOKEN_PREFIX + token);
    }
}
