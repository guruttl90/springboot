package com.spring.security.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.dao.UserDetailsRepository;
import com.spring.security.model.UserInfo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userDetailsRepository.getUserInfoByUserName(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		return new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));		
	}
		

}
