package com.vsk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Username cannot be null")
	private String username;
	@Email(message = "Email should be valid")
	private String email;
	@NotBlank(message = "Phone cannot be null")
	private String phone;

	public User() {
	}
}
