package com.spring.springauthentication.dto;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

	private static final long serialVersionUID = -1347335429848411399L;

	
	private String username;
	
	public MyUserDetails() {		
	}

	
	public MyUserDetails(String username) {		
		this.username = username;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {		
		return "123";
	}

	@Override
	public String getUsername() {		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return true;
	}

}
