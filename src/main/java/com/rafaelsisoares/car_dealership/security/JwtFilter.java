package com.rafaelsisoares.car_dealership.security;

import com.rafaelsisoares.car_dealership.services.PersonService;
import com.rafaelsisoares.car_dealership.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final PersonService personService;

    @Autowired
    public JwtFilter(TokenService tokenService, PersonService personService) {
        this.tokenService = tokenService;
        this.personService = personService;
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        String rawToken = request.getHeader("Authorization");

        return rawToken == null
                ? Optional.empty()
                : Optional.of(rawToken.replace("Bearer ", ""));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        Optional<String> token = extractToken(request);

        if(token.isPresent()) {
            String username = tokenService.validateToken(token.get());
            UserDetails user = personService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(usernamePassword);
        }
        filterChain.doFilter(request, response);
    }
}
