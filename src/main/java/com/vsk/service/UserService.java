package com.vsk.service;

import com.vsk.dto.UserDto;
import com.vsk.entity.User;
import com.vsk.repository.UserRepository;
import com.vsk.util.ObjectMapperUtils;
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
		User user = new User(username, email, phone);
		userRepository.save(user);
		return user.getId();
	}

	public List<UserDto> findAllUsers() {
		return ObjectMapperUtils.mapAll(userRepository.findAll(), UserDto.class);
	}
}
