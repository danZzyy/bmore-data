package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.Grocery;
import com.bmore.spring.model.GroceryAccessibility;

public interface GroceryAccessibilityService {

	public void persist(GroceryAccessibility g);
	
	public List<GroceryAccessibility> getAll();
}
