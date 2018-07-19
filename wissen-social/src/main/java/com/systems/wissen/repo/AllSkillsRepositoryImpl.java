package com.systems.wissen.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.AllSkill;

@Repository
@Transactional
public class AllSkillsRepositoryImpl implements AllSkillsRepository {

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AllSkill> getSkills() {
		// TODO Auto-generated method stub
		String jpql="from AllSkill";
		Query query=em.createQuery(jpql);
		return query.getResultList();
	}

}
