package com.springdemo.springredisexample.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.springredisexample.model.User;
import com.springdemo.springredisexample.repository.UserRepository;

@RestController
public class UserController {

	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping(value = "/save/{id}/{name}")
	public User saveUser(@PathVariable(name = "id") String id, @PathVariable(name = "name") String name) {
		userRepository.save(new User(id,name,"10000"));
		return userRepository.findById(id);
	}
	
	
	@GetMapping(value = "/get-all-user")
	public Map<String,User> getAll() {
		Map<String,User> allUser = userRepository.findAll();
		return allUser;
	}
}
