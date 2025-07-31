package com.rafaelsisoares.car_dealership.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    private final Algorithm algorithm;

    public TokenService() {
        this.algorithm = Algorithm.HMAC256("${authentication.jwt.secret}");
    }

    private Instant generateExpiration() {
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }

    public String generateToken(String username) {
        return JWT.create().withSubject(username).withExpiresAt(generateExpiration()).sign(algorithm);
    }

    public String validateToken(String token) {
        return JWT.require(algorithm).build().verify(token).getSubject();
    }
}
