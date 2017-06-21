package com.bmore.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class GroceryAccessibilityDao implements Dao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(Object obj) {
		em.persist(obj);

	}

	@Override
	public <T> List<T> getAll(Class<T> c) {
		TypedQuery<T> query = em.createQuery(" from " + c.getName(), c);
		return query.getResultList();
	}

}
