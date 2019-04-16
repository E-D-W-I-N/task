package com.vsk.controller;

import com.vsk.entity.User;
import com.vsk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	@ResponseBody
	public List<User> getUsers() {
		return userService.findAllUsers();
	}

	@PostMapping("/add")
	@ResponseBody
	public Long addUser(@RequestBody User user) {
		return userService.addUser(user.getUsername(), user.getEmail(), user.getPhone());
	}
}
