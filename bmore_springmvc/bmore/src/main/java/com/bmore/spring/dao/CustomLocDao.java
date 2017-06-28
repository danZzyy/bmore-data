package com.bmore.spring.dao;

import java.util.List;

public interface CustomLocDao {

	public void persist(Object loc);
	
	public <T> List<T> getAll(Class<T> c);
	
	public Object getById(int id);
	
	public void deleteItem(Object loc);
	
	public void update(Object loc);
	
}
