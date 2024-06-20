package com.example.app.service;

import com.example.app.dto.GeneralResponseWithTokenDTO;
import com.example.app.dto.LoginRequestDTO;
import com.example.app.dto.RegisterRequestDTO;

public interface AuthService {

	public GeneralResponseWithTokenDTO login(LoginRequestDTO body);
	public GeneralResponseWithTokenDTO refreshToken(String token);
	public GeneralResponseWithTokenDTO register(RegisterRequestDTO body);
}
