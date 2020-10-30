package com.spring.springauthentication.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.springauthentication.dto.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{

	
	//Method inside the loadUserByUsername returns type UserDetails
	//This userDetails information , we are going to get from the database for validation
	//UserDetails is a inbuilt interface so we need to implement it  and override the definition of the method
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return new MyUserDetails(username);
	}

}
