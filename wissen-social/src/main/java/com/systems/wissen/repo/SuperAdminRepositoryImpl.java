package com.systems.wissen.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Admin;

@Repository
public class SuperAdminRepositoryImpl implements SuperAdminRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Admin addAdmin(Admin admin) {
		return entityManager.merge(admin);
	}

	@Override
	public void removeAdmin(String admin_id) {
       entityManager.remove(entityManager.find(Admin.class, admin_id));
	}

	
	@Override
	public List<Admin> getAllAdmins() {
		String jpql="from Admin a";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
	}


}
