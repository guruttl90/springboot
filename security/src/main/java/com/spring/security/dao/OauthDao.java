package com.spring.security.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.security.model.UserEntity;

//Backend is not connected to database as of now , later we connect to mongo db
@Component
public class OauthDao {

	public UserEntity getUserDetails(String username) {
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
		grantedAuthoritiesList.add(simpleGrantedAuthority);
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		userEntity.setPassword("123");
		userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
		return userEntity;
	}
}
