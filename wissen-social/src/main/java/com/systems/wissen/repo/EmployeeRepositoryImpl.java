package com.systems.wissen.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Address;
import com.systems.wissen.model.Certification;
import com.systems.wissen.model.Employee;
import com.systems.wissen.model.Skill;
import com.systems.wissen.model.UserCredential;
import com.systems.wissen.web.EmployeeViewResponse;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Employee addEmpoloyee(Employee employee) {
		manager.merge(employee);
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		return manager.createQuery("from Employee").getResultList();
	}

	@Override
	public List<EmployeeViewResponse> getAllEmployeeViewResponse() {
		List<Employee> resultList = manager.createQuery("from Employee").getResultList();
		Stream<Employee> stream = resultList.stream();
		return getListOfEmployeeViewResponse(stream);
	}

	private List<EmployeeViewResponse> getListOfEmployeeViewResponse(Stream<Employee> stream) {
		return stream.map((e) -> {
			EmployeeViewResponse employeeViewResponse = new EmployeeViewResponse();
			employeeViewResponse.setFirstName(e.getFirstName());
			employeeViewResponse.setLastName(e.getLastName());
			employeeViewResponse.setId(e.getEmpId());
			employeeViewResponse.setUrl();
			return employeeViewResponse;
		}).collect(Collectors.toList());
	}

	@Override
	public Employee getEmployeeById(String id) {
		return manager.find(Employee.class, id);
	}

	@Override
	public List<EmployeeViewResponse> getAllPendingEmployeeViewResponse() {
		List<Employee> resultList = manager.createQuery("from Employee e where e.applicationStatus=0").getResultList();
		Stream<Employee> stream = resultList.stream();
		return getListOfEmployeeViewResponse(stream);
	}

	@Override
	public String changeEmployeeApplicationStatus(String id) {
		Employee emp = manager.find(Employee.class, id);
		if (emp != null && emp.getApplicationStatus() == 0) {
			emp.setApplicationStatus(1);
			manager.merge(emp);
			return "Status changed Successfully";
		}
		else if(emp.getApplicationStatus() == 1)
			return "Employee already approved";
		else 
			return "No employee found for EmployeeId: " + id;
		
	}

	@Override
	public void removeEmployee(String employeeId) {
		Employee find = manager.find(Employee.class, employeeId);
		if(find != null)
		manager.remove(find);
	}
}