package com.systems.wissen.repo;

import java.util.List;

import com.systems.wissen.model.Employee;
import com.systems.wissen.web.EmployeeViewResponse;
import com.systems.wissen.web.ResponseObject;

public interface EmployeeRepository {

	Employee addEmpoloyee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String id);
	List<EmployeeViewResponse> getAllEmployeeViewResponse();
	List<EmployeeViewResponse> getAllPendingEmployeeViewResponse();
	ResponseObject changeEmployeeApplicationStatus(String id);
	void removeEmployee(String employeeId);
	List<Employee> getAllApprovedEmployees();
}
	