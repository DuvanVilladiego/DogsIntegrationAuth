package com.example.app.controller.model;

import com.example.app.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
    private String name;
	@Column(nullable = false)
    private String email;
	@Column(nullable = false)
    private String password;
	
	public static UserDTO toDto(UserEntity user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
	}
	
}
