package com.example.app.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.app.dto.UserDTO;
import com.example.app.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Value("${SUPER_SECRET_KEY}")
	private String secret;

	@Override
	public String generateToken(UserDTO user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);

			String token = JWT.create()
					.withIssuer("login-auth-api")
					.withSubject(user.getEmail())
					.withExpiresAt(this.generateExpirationDate())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while authenticating");
		}
	}

	@Override
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("login-auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return null;
		}
	}

	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
}
