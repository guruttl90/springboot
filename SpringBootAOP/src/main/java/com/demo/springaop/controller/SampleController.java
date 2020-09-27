package com.demo.springaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springaop.business.SampleBusiness;

@RestController
@RequestMapping(value = "/")
public class SampleController {

	private SampleBusiness sampleBusiness;
	
	public SampleController(SampleBusiness sampleBusiness) {
		this.sampleBusiness = sampleBusiness;		
	}
	
	//Here autowiring is done using constructor
	
	@GetMapping(value = "/get")
	public String get(){
		return sampleBusiness.get();
	}
	
	
}
