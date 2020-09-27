package com.spring.springauthentication.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springauthentication.dto.MyUserDetails;
import com.spring.springauthentication.model.AuthenticationRequest;
import com.spring.springauthentication.model.AuthenticationResponse;
import com.spring.springauthentication.service.MyUserDetailsService;
import com.spring.springauthentication.util.JwtUtil;
@RestController
public class HomeController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping(value = "/")
	public String common() {
		return "Your are Public!!!";
	}
	
	@GetMapping(value = "/user")
	public String getUser() {
		return "Your are User!!!";
	}
	
	@GetMapping(value = "/admin")
	public String getAdmin() {
		return "Your are Admin!!!";
	}
	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(value = "/login")
	public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		
		MyUserDetails myUserDetails= (MyUserDetails) myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		String token = jwtUtil.generateToken(myUserDetails);
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setJwtToken(token);
		return response;
	}
}
