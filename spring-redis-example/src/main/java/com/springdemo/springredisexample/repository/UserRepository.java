package com.springdemo.springredisexample.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springdemo.springredisexample.model.User;

@Repository
public interface UserRepository {
	public void save(User user);
	public Map<String, User> findAll();
	public User findById(String id);
}
