package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.Dao;
import com.bmore.spring.model.Accessibility;
import com.bmore.spring.model.GroceryAccessibility;

@Service("groceryAccessibilityService")
public class GroceryAccessibilityServiceImpl implements GroceryAccessibilityService {

	@Autowired
	Dao dao;
	@Override
	public void persist(GroceryAccessibility a) {
		dao.persist(a);

	}

	@Override
	public List<GroceryAccessibility> getAll() {
		return dao.getAll(GroceryAccessibility.class);
	}

}
