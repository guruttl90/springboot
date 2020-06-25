package com.spring.demoexceptionhandling.service;

public class EmployeeServiceException extends Exception{
	
	private static final long serialVersionUID = -4502064148639345857L;

	public EmployeeServiceException() {
		super();
	}

	public EmployeeServiceException(final String message) {
		super(message);
	}
}
