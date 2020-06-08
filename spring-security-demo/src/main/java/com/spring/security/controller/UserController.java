package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.dao.UserDetailsRepository;
import com.spring.security.model.UserInfo;

@RestController
public class UserController {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@GetMapping("/get-all-user")
	public ResponseEntity<List<UserInfo>> getAllUser() {
		List<UserInfo> userInfo = userDetailsRepository.getAllUser();
		if (userInfo == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}
	
	
	@GetMapping("/get-details-by-username")
	public ResponseEntity<List<UserInfo>> getUserDetails(String username) {
		List<UserInfo> userInfo = userDetailsRepository.getAllUser();
		if (userInfo == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}
}
