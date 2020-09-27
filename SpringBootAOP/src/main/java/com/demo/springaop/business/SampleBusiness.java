package com.demo.springaop.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleBusiness {
	private static final Logger log = LoggerFactory.getLogger(SampleBusiness.class);
	public String get() {
		log.info("inside the SampleBusiness method name get");
		return "Hello world";
	}
}
