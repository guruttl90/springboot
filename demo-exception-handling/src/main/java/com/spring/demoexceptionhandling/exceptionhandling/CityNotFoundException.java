package com.spring.demoexceptionhandling.exceptionhandling;

public class CityNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -5403469555915948652L;

	public CityNotFoundException(Long id) {
        super(String.format("City with Id %d not found", id));
    }
	
	public CityNotFoundException(String msg) {
        super(msg);
    }
}
