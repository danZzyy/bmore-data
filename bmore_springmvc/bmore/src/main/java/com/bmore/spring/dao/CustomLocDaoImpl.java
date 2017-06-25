package com.bmore.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.bmore.spring.model.CustomLocation;

@Component
public class CustomLocDaoImpl implements CustomLocDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
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
	public void deleteById(int id) {
		CustomLocation toDelete = em.find(CustomLocation.class, id);
		em.remove(toDelete);
	}

	@Override
	public void update(Object loc) {
		CustomLocation toUpdate = (CustomLocation) em.find(CustomLocation.class, ((CustomLocation) loc).getId());
		if(toUpdate != null){
			CustomLocation CLoc = (CustomLocation) loc;
			toUpdate.setAddress(CLoc.getAddress());
			toUpdate.setAccessibility(CLoc.getAccessibility());
			toUpdate.setName(CLoc.getName());
			toUpdate.setLat(CLoc.getLat());
			toUpdate.setLng(CLoc.getLng());
			em.persist(toUpdate);
		}
	}

}
