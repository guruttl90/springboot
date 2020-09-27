package com.spring.demoexceptionhandling.exceptionhandling;

public class NoDataFoundException extends RuntimeException{

	private static final long serialVersionUID = -6761443237163460484L;

	public NoDataFoundException() {

        super("No data found");
    }
}
