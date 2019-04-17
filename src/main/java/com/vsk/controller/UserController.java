package com.vsk.controller;

import com.vsk.dto.UserDto;
import com.vsk.entity.User;
import com.vsk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/all")
	public ResponseEntity<User> getUsers() {
		List<UserDto> users = userService.findAllUsers();
		if (users.isEmpty())
			throw new EntityNotFoundException("There's no users");
		else
			return new ResponseEntity(userService.findAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public Long addUser(@Valid @RequestBody UserDto userDto) {
		return userService.addUser(userDto.getUsername(), userDto.getEmail(), userDto.getPhone());
	}
}