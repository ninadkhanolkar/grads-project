package com.systems.wissen.repo;

import java.util.List;

import com.systems.wissen.model.Employee;

public interface EmployeeRepository {

	Employee addEmpoloyee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String id);
}
