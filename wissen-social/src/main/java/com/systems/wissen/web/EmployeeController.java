package com.systems.wissen.web;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systems.wissen.model.Employee;
import com.systems.wissen.repo.EmployeeRepository;
import com.systems.wissen.service.EmployeeRegistrationService;

@RestController
public class EmployeeController {
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeRegistrationService registrationService;
	
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
	
	@RequestMapping(value="/api/wiseconnect/v1/employee", method=RequestMethod.POST)
	public Map post(@RequestBody Map jo)
	{	
		JSONObject resultObject = new JSONObject(jo);
		registrationService.registerEmployee(resultObject);
		return jo;
	}
}
