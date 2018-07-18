package com.systems.wissen.repo;

import java.util.List;

import org.json.simple.JSONObject;

import com.systems.wissen.model.Employee;
import com.systems.wissen.web.EmployeeViewResponse;

public interface EmployeeRepository {

	Employee addEmpoloyee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String id);
	List<EmployeeViewResponse> getAllEmployeeViewResponse();
	List<EmployeeViewResponse> getAllPendingEmployeeViewResponse();
	String changeEmployeeApplicationStatus(String id);
	void removeEmployee(String employeeId);
}
