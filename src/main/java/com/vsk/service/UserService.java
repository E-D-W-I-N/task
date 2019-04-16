package com.vsk.service;

import com.vsk.entity.User;
import com.vsk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Long addUser(String username, String email, String phone) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);

		userRepository.save(user);

		return user.getId();
	}

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
}
