package com.systems.wissen.repo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Employee;
import com.systems.wissen.web.EmployeeViewResponse;
import com.systems.wissen.web.ResponseObject;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllApprovedEmployees() {
		return manager.createQuery("from Employee e where e.applicationStatus = 1").getResultList();
	}

	@Override
	public List<EmployeeViewResponse> getAllEmployeeViewResponse() {
		@SuppressWarnings("unchecked")
		List<Employee> resultList = manager.createQuery("from Employee where e.applicationStatus = 1").getResultList();
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
		@SuppressWarnings("unchecked")
		List<Employee> resultList = manager.createQuery("from Employee e where e.applicationStatus=0").getResultList();
		Stream<Employee> stream = resultList.stream();
		return getListOfEmployeeViewResponse(stream);
	}

	@Override
	public ResponseObject changeEmployeeApplicationStatus(String id) {
		Employee emp = manager.find(Employee.class, id);
		ResponseObject responseObject = new ResponseObject();
		if (emp != null && emp.getApplicationStatus() == 0) {
			emp.setApplicationStatus(1);
			manager.merge(emp);
			responseObject.setMessage("Status changed Successfully");
			return responseObject;
		} else if (emp != null && emp.getApplicationStatus() == 1) {
			responseObject.setMessage("Status changed Successfully");
			return responseObject;
		} else {
			responseObject.setMessage("No employee found for EmployeeId: " + id);
			return responseObject;
		}
	}

	@Override
	public void removeEmployee(String employeeId) {
		Employee find = manager.find(Employee.class, employeeId);
		if (find != null)
			manager.remove(find);
	}
}