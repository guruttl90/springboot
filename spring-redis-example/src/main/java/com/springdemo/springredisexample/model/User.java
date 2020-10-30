package com.springdemo.springredisexample.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3337368498459307270L;
	
	private String id;
	private String name;
	private String salary;
	
	public User(String id, String name, String salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
}
