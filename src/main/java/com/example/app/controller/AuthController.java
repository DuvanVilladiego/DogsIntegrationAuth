package com.example.app.controller;

import org.springframework.http.ResponseEntity;

import com.example.app.dto.GeneralResponseWithTokenDTO;
import com.example.app.dto.LoginRequestDTO;
import com.example.app.dto.RegisterRequestDTO;

public interface AuthController {
	
	public ResponseEntity<GeneralResponseWithTokenDTO> login(LoginRequestDTO request);
	public ResponseEntity<GeneralResponseWithTokenDTO> register(RegisterRequestDTO request);
	public ResponseEntity<GeneralResponseWithTokenDTO> refreshToken();
	
}
