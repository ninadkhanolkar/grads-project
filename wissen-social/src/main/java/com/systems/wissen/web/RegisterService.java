package com.systems.wissen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systems.wissen.model.Employee;
import com.systems.wissen.repo.EmployeeRepository;

@RestController
public class RegisterService {
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value="/api/wiseconnect/v1", method=RequestMethod.POST)
	public Employee post(@RequestBody Employee employee)
	{
		Employee addedEmployee = employeeRepository.addEmpoloyee(employee);
		return addedEmployee;
	}
}