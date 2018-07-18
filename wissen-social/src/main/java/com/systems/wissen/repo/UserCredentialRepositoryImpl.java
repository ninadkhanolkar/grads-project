package com.systems.wissen.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.UserCredential;

@Repository
@Transactional
public class UserCredentialRepositoryImpl implements UserCredentialRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserCredential addUserCredential(UserCredential user) {
		
		return manager.merge(user);
	}

}
