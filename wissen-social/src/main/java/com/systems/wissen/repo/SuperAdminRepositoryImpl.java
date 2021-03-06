package com.systems.wissen.repo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Admin;

@Repository
@Transactional
public class SuperAdminRepositoryImpl implements SuperAdminRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Admin addAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return entityManager.merge(admin);
	}

	@Override
	public void removeAdmin(String id) {
		String jpql="delete from Admin a where a.id=?";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<AdminDao> getAdminDao() {
		List<Admin> list = entityManager.createQuery("from Admin a", Admin.class).getResultList();
		Stream<Admin> stream = list.stream();
		return getAdminDTOResponse(stream);
	}

	private List<AdminDao> getAdminDTOResponse(Stream<Admin> stream) {
		return stream.map(a -> {
			AdminDao adminDTO = new AdminDao();
			adminDTO.setId(a.getId());
			adminDTO.setRole(a.getRole().getRoleId());
			return adminDTO;
		}).collect(Collectors.toList());
	}

	public Admin getAdmin(String id) {
		String jpql = "from Admin a where a.id=?";
		TypedQuery<Admin> query = entityManager.createQuery(jpql, Admin.class);
		query.setParameter(0, id);
		return query.getSingleResult();
	}
}
