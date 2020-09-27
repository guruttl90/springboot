package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/security-demo")
public class SampleController {
	
	@GetMapping(value = "/get-data")
	public String getData() {		
		return "success";
	}

}
