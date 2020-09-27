package com.spring.springauthentication.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable{
	
	private static final long serialVersionUID = -3708963420874603261L;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
