package com.example.springAcutator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	@GetMapping(value = "/get-test")
	public String getData() {
		log.info("inside method getData - SampleController");
		return "welcome to get method";
	}
	
	@PutMapping(value = "/put-test")
	public String patchMethod() {
		log.info("inside method patchMethod - SampleController");
		return "welcome to patch method";
	}
	
	@PatchMapping(value = "/patch-test")
	public String putMethod() {
		log.info("inside method putMethod - SampleController");
		return "welcome to put method";
	}
}
