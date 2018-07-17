package com.systems.wissen.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Employee;

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
	public Employee getEmployeeById(String id) {
		return manager.find(Employee.class, id);
	}

}