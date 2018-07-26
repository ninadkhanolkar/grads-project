package com.systems.wissen.repo;

import java.util.List;

import org.json.simple.JSONObject;

import com.systems.wissen.model.Employee;
import com.systems.wissen.web.EmployeeViewResponse;
import com.systems.wissen.web.ResponseMessage;

public interface EmployeeRepository {
	Employee addEmpoloyee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(String id);

	List<EmployeeViewResponse> getAllEmployeeViewResponse();

	List<EmployeeViewResponse> getAllPendingEmployeeViewResponse();

	ResponseMessage changeEmployeeApplicationStatus(String id);

	void removeEmployee(String employeeId);

	List<Employee> getAllApprovedEmployees();

	List<EmployeeViewResponse> getReporteeOfEmployee(String empId);

	void registerEmployee(JSONObject registrationObject);
}
