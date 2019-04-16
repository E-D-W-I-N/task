package com.vsk.controller;

import com.vsk.dto.UserDto;
import com.vsk.entity.User;
import com.vsk.service.UserService;
import com.vsk.util.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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
		return new ResponseEntity(userService.findAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
			return new ResponseEntity(errorsMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(
				userService.addUser(userDto.getUsername(), userDto.getEmail(), userDto.getPhone()), HttpStatus.OK);
	}
}