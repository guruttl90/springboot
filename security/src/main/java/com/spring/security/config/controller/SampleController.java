package com.spring.security.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@RequestMapping("/")
	public String home() {
	      return "Hello World";
	 }
	
	@RequestMapping("/test")
	public Test getData() {
		Test response = new Test("XYZ"); 
		return response;
	 }
}

class Test{
	
	private String test;

	public Test(String test) {
		super();
		this.test = test;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
}