package com.springdemo.springredisexample.repository.impl;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springdemo.springredisexample.model.User;
import com.springdemo.springredisexample.repository.UserRepository;


@Service
public class UserRepositoryImpl implements UserRepository{

	private RedisTemplate<String, User> redisTemplate;
	private HashOperations<String, String, User> hashOperations;
	
	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = this.redisTemplate.opsForHash();
	}
	
	@Override
	public void save(User user) {		
		hashOperations.put("USER", user.getId(), user);
	}

	@Override
	public Map<String, User> findAll() {		
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(String id) {
		
		return hashOperations.get("USER", id);
	}	
	
}
