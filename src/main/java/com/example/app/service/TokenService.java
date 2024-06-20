package com.example.app.service;

import com.example.app.dto.UserDTO;

public interface TokenService {
	
	public String validateToken(String token);
	public String generateToken(UserDTO user);
	public String refreshToken(String token);

}
