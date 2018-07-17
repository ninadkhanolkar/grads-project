package com.systems.wissen.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/api/wiseconnect/v1/employee", method=RequestMethod.POST)
	public Employee post(@RequestBody Employee employee)
	{
		Employee addedEmployee = employeeRepository.addEmpoloyee(employee);
		return addedEmployee;
	}
	
	@RequestMapping(value="/api/wiseconnect/v1/employee", method=RequestMethod.GET)
	public List<Employee> get()
	{
		List<Employee> allEmployees = employeeRepository.getAllEmployees();
		return allEmployees;
	}
	
	@RequestMapping(value="/api/wiseconnect/v1/employee/{employeeId}", method=RequestMethod.GET)
	public Employee get(@PathVariable String employeeId)
	{
		Employee employee = employeeRepository.getEmployeeById(employeeId);
		return employee;
	}
	
	@RequestMapping(value="/api/wiseconnect/v1/employeeResponse", method=RequestMethod.GET)
	public List<EmployeeViewResponse> getAll()
	{
		List<EmployeeViewResponse> allEmployeeViewResponse = employeeRepository.getAllEmployeeViewResponse();
		return allEmployeeViewResponse;
	}
}
