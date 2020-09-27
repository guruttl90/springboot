package com.spring.demoexceptionhandling.exceptionhandling;

public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = 2928190270026541681L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
