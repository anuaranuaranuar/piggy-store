package com.anuar.piggy_store.service;

import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("firma");
            return JWT.create()
                    .withIssuer("empresa")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("error al generar el token jwt", exception);
        }
    }

    public Instant expirationDate() {
        return LocalDateTime.now().plusHours(72).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("firma");
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("empresa")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token invalido o caducado");
        }
    }
}
