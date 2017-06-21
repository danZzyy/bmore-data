package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.Dao;
import com.bmore.spring.model.Trail;

@Service("trailService")
public class TrailServiceImpl implements TrailService {

	@Autowired
	Dao dao;
	
	@Override
	public void persist(Trail g) {
		// TODO Auto-generated method stub
		dao.persist(g);

	}

	@Override
	public List<Trail> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll(Trail.class);
	}

}
