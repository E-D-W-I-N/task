package com.vsk.controller;

import com.vsk.dto.UserDto;
import com.vsk.service.UserService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getUsers() {
		List<UserDto> users = userService.findAllUsers();
		if (users.isEmpty())
			throw new EntityNotFoundException("There's no users");
		else
			return new ResponseEntity(userService.findAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public Long addUser(
			@NotBlank(message = "Username can't be null") @RequestParam String username,

			@NotBlank(message = "Email can't be null")
			@Email(message = "Email must be valid") @RequestParam String email,

			@Pattern(regexp = "\\d(-\\d{3}){2}-\\d{4}",
					message = "Please, use right phone number format. For example: 8-903-503-9832")
			@NotBlank(message = "Phone can't be null") @RequestParam String phone) throws TypeMismatchException {
		return userService.addUser(username, email, phone);
	}
}