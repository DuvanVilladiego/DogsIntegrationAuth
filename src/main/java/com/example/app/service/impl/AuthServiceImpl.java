package com.example.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.controller.model.UserEntity;
import com.example.app.controller.model.repository.UserRepository;
import com.example.app.dto.GeneralResponseWithTokenDTO;
import com.example.app.dto.LoginRequestDTO;
import com.example.app.dto.RegisterRequestDTO;
import com.example.app.dto.UserDTO;
import com.example.app.service.AuthService;
import com.example.app.service.TokenService;
import com.example.app.utils.Constants;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenService tokenService;

	@Override
	public GeneralResponseWithTokenDTO login(LoginRequestDTO body) {
		try {
			UserEntity user = this.repository.findByEmail(body.getEmail())
					.orElseThrow(() -> new RuntimeException(Constants.USER_NOT_FOUND_MESSAGE));
			if (passwordEncoder.matches(body.getPassword(), user.getPassword())) {
				String token = this.tokenService.generateToken(UserEntity.toDto(user));
				return new GeneralResponseWithTokenDTO(true, Constants.SUCCESS_MESSAGE, null, token);
			}
			return new GeneralResponseWithTokenDTO(false, Constants.USER_NOT_FOUND_MESSAGE, null, null);
		} catch (Exception e) {
			return new GeneralResponseWithTokenDTO(false, e.getMessage(), null, null);
		}
	}
	
	@Override
	public GeneralResponseWithTokenDTO register(RegisterRequestDTO body) {
		try {				
	        Optional<UserEntity> user = this.repository.findByEmail(body.getEmail());
	        if(user.isEmpty()) {
	        	UserDTO newUser = new UserDTO();
	            newUser.setEmail(body.getEmail());
	            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
	            newUser.setName(body.getName());
	            this.repository.save(UserDTO.toEntity(newUser));
	            String token = this.tokenService.generateToken(newUser);
	            return new GeneralResponseWithTokenDTO(false, Constants.SUCCESS_MESSAGE, null, token);
	        } else {
	        	return new GeneralResponseWithTokenDTO(false, Constants.USER_ALREADY_EXISTS_MESSAGE, null, null);
	        }
		} catch (Exception e) {
			return new GeneralResponseWithTokenDTO(false, e.getMessage(), null, null);
		}
	}

	@Override
	public GeneralResponseWithTokenDTO refreshToken() {
		// TODO Auto-generated method stub
		return null;
	}

}
