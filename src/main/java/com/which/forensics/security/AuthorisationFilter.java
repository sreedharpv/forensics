package com.which.forensics.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorisationFilter extends BasicAuthenticationFilter {
    public AuthorisationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstant.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(request));
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String header = request.getHeader(SecurityConstant.HEADER_AUTHORIZATION);
        header = header.replace(SecurityConstant.TOKEN_PREFIX, "");
        String subject = Jwts.parser()
                .setSigningKey(SecurityConstant.getTokenSecret())//SecurityConstant.SECURITY_TOKEN)
                .parseClaimsJws(header)
                .getBody()
                .getSubject();
        return new UsernamePasswordAuthenticationToken(subject, null, new ArrayList<>());
    }
}
