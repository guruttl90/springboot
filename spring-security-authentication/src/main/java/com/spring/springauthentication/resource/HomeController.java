package com.spring.springauthentication.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {

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
}
