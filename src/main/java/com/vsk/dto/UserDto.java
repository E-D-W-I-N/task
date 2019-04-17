package com.vsk.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

	@NotBlank(message = "Username can't be null")
	private String username;

	@NotBlank(message = "Email can't be null")
	@Email(message = "Email must be valid")
	private String email;

	@Pattern(regexp = "\\+\\d(-\\d{3}){2}-\\d{4}",
			message = "Please, use right phone number format. For example: +7-903-503-9832")
	@NotBlank(message = "Phone can't be null")
	private String phone;
}
