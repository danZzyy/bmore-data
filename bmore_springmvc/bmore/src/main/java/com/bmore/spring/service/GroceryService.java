package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.Grocery;

public interface GroceryService {
	
	public void persist(Grocery g);
	
	public List<Grocery> getAll();
}
