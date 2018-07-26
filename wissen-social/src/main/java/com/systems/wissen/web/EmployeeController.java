package com.systems.wissen.web;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systems.wissen.model.Employee;
import com.systems.wissen.repo.EmployeeRepository;
import com.systems.wissen.repo.UserCredentialRepository;

@RestController
@CrossOrigin(origins = { "*" })
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@RequestMapping(value = "/api/wiseconnect/v1/admins/employees", method = RequestMethod.GET)
	public List<Employee> get() {
		return employeeRepository.getAllEmployees();
	}

	@RequestMapping(value = "/api/wiseconnect/v1/employees/{employeeId}", method = RequestMethod.GET)
	public Employee get(@PathVariable String employeeId) {
		Employee employee = employeeRepository.getEmployeeById(employeeId);
		employee.setManagerId();
		return employee;
	}

	@RequestMapping(value = "/api/wiseconnect/v1/employees/approved-employees", method = RequestMethod.GET)
	public List<EmployeeViewResponse> getAll() {
		return employeeRepository.getAllEmployeeViewResponse();
	}

	@RequestMapping(value = "/api/wiseconnect/v1/{empId}/reportees", method = RequestMethod.GET)
	public List<EmployeeViewResponse> getReportees(@PathVariable String empId) {
		return employeeRepository.getReporteeOfEmployee(empId);
	}

	@RequestMapping(value = "/api/wiseconnect/v1/admins/pendingEmployees", method = RequestMethod.GET)
	public List<EmployeeViewResponse> getPendingEmployee() {
		return employeeRepository.getAllPendingEmployeeViewResponse();
	}

	@RequestMapping(value = "/api/wiseconnect/v1/employee", method = RequestMethod.POST)
	public Map<Object, Object> post(@RequestBody Map<Object, Object> registrationObject) {
		JSONObject resultObject = new JSONObject(registrationObject);
		employeeRepository.registerEmployee(resultObject);
		return registrationObject;
	}

	@RequestMapping(value = "/api/wiseconnect/v1/admins/employees/{employeeId}", method = RequestMethod.PUT)
	public ResponseMessage put(@PathVariable String employeeId) {
		return employeeRepository.changeEmployeeApplicationStatus(employeeId);
	}

	@RequestMapping(value = "/api/wiseconnect/v1/admins/employees/{employeeId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String employeeId) {
		userCredentialRepository.removeUserCredential(employeeId);
		employeeRepository.removeEmployee(employeeId);
	}

}