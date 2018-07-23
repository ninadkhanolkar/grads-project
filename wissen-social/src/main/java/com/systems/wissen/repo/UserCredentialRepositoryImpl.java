package com.systems.wissen.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.UserCredential;

@Repository
@Transactional
public class UserCredentialRepositoryImpl implements UserCredentialRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserCredential addUserCredential(UserCredential user) {
		return manager.merge(user);
	}

	@Override
	public void removeUserCredential(String employeeId) {
		String jpql="from UserCredential u where u.employee.empId = :empId";
		Query query=manager.createQuery(jpql);
		query.setParameter("empId", employeeId);
		UserCredential userCredential =(UserCredential) query.getResultList().get(0);
		if (userCredential != null) {
			manager.remove(userCredential);
		}
	}
}
