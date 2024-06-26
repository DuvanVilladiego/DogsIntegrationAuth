package com.example.app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.utils.Constants;

import jakarta.servlet.http.HttpServletRequest;

import com.example.app.controller.AuthController;
import com.example.app.dto.GeneralResponseWithTokenDTO;
import com.example.app.dto.LoginRequestDTO;
import com.example.app.dto.RegisterRequestDTO;
import com.example.app.service.AuthService;

@RestController
@RequestMapping(Constants.AUTHENTICATION_PATH)
public class AuthControllerImpl implements AuthController {

	@Autowired
	private AuthService service;

	@PostMapping(Constants.LOGIN_PATH)
	@Override
	public ResponseEntity<GeneralResponseWithTokenDTO> login(@RequestBody LoginRequestDTO request) {
		GeneralResponseWithTokenDTO response = service.login(request);
		if (response.isStatus()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping(Constants.REGISTER_PATH)
	@Override
	public ResponseEntity<GeneralResponseWithTokenDTO> register(@RequestBody RegisterRequestDTO request) {
		GeneralResponseWithTokenDTO response = service.register(request);
		if (response.isStatus()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping(Constants.REFRESH_TOKEN_PATH)
	@Override
	public ResponseEntity<GeneralResponseWithTokenDTO> refreshToken(HttpServletRequest request) {
		 String authorizationHeader = request.getHeader("Authorization");
		    String token = null;
		    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		        token = authorizationHeader.substring(7);
		    }

		    if (token != null) {
		        GeneralResponseWithTokenDTO response = service.refreshToken(token);
		        if (response.isStatus()) {
		            return ResponseEntity.ok(response);
		        } else {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		        }
		    } else {
		        GeneralResponseWithTokenDTO response = new GeneralResponseWithTokenDTO(false, Constants.UNAUTH_USER_ERR, null, null);
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		    }
	}
}
