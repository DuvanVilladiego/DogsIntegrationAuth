package com.example.app.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.app.dto.UserDTO;
import com.example.app.service.TokenService;
import com.example.app.utils.Constants;

@Service
public class TokenServiceImpl implements TokenService {

	private static final Logger LOG = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Value("${SUPER_SECRET_KEY}")
	private String secret;

	@Override
	public String generateToken(UserDTO user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);

			String token = JWT.create().withIssuer(Constants.LOGIN_AUTH_API).withSubject(user.getEmail())
					.withExpiresAt(this.generateExpirationDate()).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			LOG.error(String.format(Constants.AUTH_ERR, e.getMessage()));
			throw new RuntimeException(String.format(Constants.AUTH_ERR, e.getMessage()));
		}
	}

	@Override
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer(Constants.LOGIN_AUTH_API).build().verify(token).getSubject();
		} catch (JWTVerificationException e) {
			LOG.error(String.format(Constants.ERR_VALIDATE_TOKEN, e.getMessage()));
			return null;
		}
	}


	@Override
	public String refreshToken(String token) {
		LOG.info(Constants.REFRESH_TOKEN_INIT);
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String subject = JWT.require(algorithm).withIssuer(Constants.LOGIN_AUTH_API).build().verify(token)
					.getSubject();
			String refreshedToken = JWT.create().withIssuer(Constants.LOGIN_AUTH_API).withSubject(subject)
					.withExpiresAt(generateExpirationDate()).sign(algorithm);
			return refreshedToken;
		} catch (JWTVerificationException e) {
			LOG.error(String.format(Constants.ERR_VALIDATE_TOKEN, e.getMessage()));
			return null;
		} catch (JWTCreationException e) {
			LOG.error(String.format(Constants.ERR_REFRESH, e.getMessage()));
			throw new RuntimeException(String.format(Constants.ERR_REFRESH, e.getMessage()));
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
}
