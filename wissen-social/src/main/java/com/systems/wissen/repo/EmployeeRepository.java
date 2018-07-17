package com.systems.wissen.repo;

import java.util.List;

import com.systems.wissen.model.Employee;
import com.systems.wissen.web.EmployeeViewResponse;

public interface EmployeeRepository {

	Employee addEmpoloyee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String id);
	List<EmployeeViewResponse> getAllEmployeeViewResponse();
}
