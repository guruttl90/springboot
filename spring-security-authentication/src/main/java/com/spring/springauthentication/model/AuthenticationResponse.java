package com.spring.springauthentication.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{
	
	private static final long serialVersionUID = -7978581555019497877L;

	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}	
	
}
