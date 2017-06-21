package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.Dao;
import com.bmore.spring.model.Accessibility;
import com.bmore.spring.model.Grocery;

@Service("accessibilityService")
public class AccessibilityServiceImpl implements AccessibilityService {

	@Autowired
	Dao dao;
	@Override
	public void persist(Accessibility a) {
		dao.persist(a);

	}

	@Override
	public List<Accessibility> getAll() {
		return dao.getAll(Accessibility.class);
	}

}