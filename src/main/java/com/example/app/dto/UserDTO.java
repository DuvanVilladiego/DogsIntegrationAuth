package com.example.app.dto;

import com.example.app.controller.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
    private String name;
    private String email;
    private String password;

	public static UserEntity toEntity(UserDTO user) {
		return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPassword());
	}
}
