package com.systems.wissen.repo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Employee;
import com.systems.wissen.model.UserCredential;
import com.systems.wissen.service.EmailService;
import com.systems.wissen.service.RegistrationService;
import com.systems.wissen.web.EmployeeViewResponse;
import com.systems.wissen.web.ResponseMessage;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private EmailService emailService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Override
	public Employee addEmpoloyee(Employee employee) {
		manager.merge(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = manager.createQuery("from Employee", Employee.class);
		List<Employee> resultList = query.getResultList();
		setManagerId(resultList);
		return resultList;
	}

	private void setManagerId(List<Employee> resultList) {
		resultList.forEach((employee) -> {
			employee.setManagerId();
		});
	}

	@Override
	public List<Employee> getAllApprovedEmployees() {
		TypedQuery<Employee> query = manager.createQuery("from Employee e where e.applicationStatus = 1",
				Employee.class);
		List<Employee> resultList = query.getResultList();
		setManagerId(resultList);
		return resultList;
	}

	@Override
	public List<EmployeeViewResponse> getAllEmployeeViewResponse() {
		List<Employee> resultList = manager.createQuery("from Employee e where e.applicationStatus = 1", Employee.class)
				.getResultList();
		Stream<Employee> stream = resultList.stream();
		return getListOfEmployeeViewResponse(stream);
	}

	private List<EmployeeViewResponse> getListOfEmployeeViewResponse(Stream<Employee> stream) {
		return stream.map((e) -> {
			EmployeeViewResponse employeeViewResponse = new EmployeeViewResponse();
			employeeViewResponse.setFirstName(e.getFirstName());
			employeeViewResponse.setLastName(e.getLastName());
			employeeViewResponse.setId(e.getEmpId());
			return employeeViewResponse;
		}).collect(Collectors.toList());
	}

	@Override
	public Employee getEmployeeById(String id) {
		return manager.find(Employee.class, id);
	}

	@Override
	public List<EmployeeViewResponse> getAllPendingEmployeeViewResponse() {
		List<Employee> resultList = manager.createQuery("from Employee e where e.applicationStatus=0", Employee.class)
				.getResultList();
		Stream<Employee> stream = resultList.stream();
		return getListOfEmployeeViewResponse(stream);
	}

	@Override
	public List<EmployeeViewResponse> getReporteeOfEmployee(String empId) {
		String jpql = "from Employee e where e.employee.empId=?";
		TypedQuery<Employee> query = manager.createQuery(jpql, Employee.class);
		query.setParameter(0, empId);
		List<Employee> employees = query.getResultList();
		Stream<Employee> stream = employees.stream();
		return getListOfEmployeeViewResponse(stream);

	}

	@Override
	public ResponseMessage changeEmployeeApplicationStatus(String id) {
		Employee emp = manager.find(Employee.class, id);
		ResponseMessage responseObject = new ResponseMessage();
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
		if (find != null) {
			manager.remove(find);
			emailService.sendEmail(find);
		}
	}

	@Override
	public void registerEmployee(JSONObject registrationObject) {
		String empId = (String) registrationObject.get("empId");
		removeEmployee(empId);
		userCredentialRepository.removeUserCredential(empId);
		Map<String, Object> registerEmployee = registrationService.registerEmployee(registrationObject);
		addEmpoloyee((Employee) registerEmployee.get("employeeRepository"));
		UserCredential credential = (UserCredential) registerEmployee.get("userCredentialRepository");
		userCredentialRepository.addUserCredential(credential);
	}
}
