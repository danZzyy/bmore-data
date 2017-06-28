package com.bmore.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bmore.spring.model.CustomLocation;

@Component
public class CustomLocDaoImpl implements CustomLocDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void persist(Object loc) {
		em.persist(loc);
	}

	@Override
	public <T> List<T> getAll(Class<T> c) {
		TypedQuery<T> query = em.createQuery(" from " + c.getName(), c);
		return query.getResultList();
	}

	@Override
	public Object getById(int id) {
		return em.find(CustomLocation.class, id);
	}

	@Override
	@Transactional
	public void deleteItem(Object loc) {
		em.remove(em.getReference(CustomLocation.class, ((CustomLocation) loc).getId()));
	}

	@Override
	@Transactional
	public void update(Object loc) {
		em.persist(loc);
	}

}
