package com.spring.security.config;

import org.springframework.security.core.userdetails.User;

import com.spring.security.model.UserEntity;

public class CustomUser extends User{
	private static final long serialVersionUID = 517479756383163952L;

	public CustomUser(UserEntity userEntity) {
		super(userEntity.getUsername(),userEntity.getPassword(),userEntity.getGrantedAuthoritiesList());
	}
}
