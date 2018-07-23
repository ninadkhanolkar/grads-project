package com.systems.wissen.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.AllSkill;

@Repository
@Transactional
public class AllSkillsRepositoryImpl implements AllSkillsRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<AllSkill> getSkills() {
		String jpql="from AllSkill";
		TypedQuery<AllSkill> query=em.createQuery(jpql,AllSkill.class);
		return query.getResultList();
	}
}
