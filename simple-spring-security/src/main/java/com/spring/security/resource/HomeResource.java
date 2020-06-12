package com.spring.security.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	@GetMapping(value = "/")
	public String get() {
		return "Your are authorized";
	}
}
