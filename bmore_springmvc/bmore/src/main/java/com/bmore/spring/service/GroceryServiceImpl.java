package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.Dao;
import com.bmore.spring.model.Grocery;

@Service("groceryService")
public class GroceryServiceImpl implements GroceryService {

	@Autowired
	Dao dao;
	@Override
	public void persist(Grocery g) {
		// TODO Auto-generated method stub
		dao.persist(g);

	}

	@Override
	public List<Grocery> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll(Grocery.class);
	}

}
