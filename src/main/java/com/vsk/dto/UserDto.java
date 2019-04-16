package com.vsk.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

	@NotBlank(message = "Username can't be null")
	private String username;

	@Email(message = "Email must be valid")
	private String email;

	@NotBlank(message = "Phone can't be null")
	private String phone;
}
