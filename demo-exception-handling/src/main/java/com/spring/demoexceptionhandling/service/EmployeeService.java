package com.spring.demoexceptionhandling.service;

import org.springframework.stereotype.Service;

import com.spring.demoexceptionhandling.model.Employee;

@Service
public class EmployeeService {

	public Employee getEmployee() throws EmployeeServiceException {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);

		return emp;
	}
	
	public Employee getEmployeeNull() throws EmployeeServiceException {
		return null;
	}
	
	public Employee getEmployeeException() throws EmployeeServiceException {
		throw new EmployeeServiceException();
	}
	
	@SuppressWarnings("unused")
	public Employee getEmp4() {
		Employee obj = null;
		try {
			if(obj == null)
				throw new NullPointerException("checking");
		}catch(Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
		return obj;			
	}
}
