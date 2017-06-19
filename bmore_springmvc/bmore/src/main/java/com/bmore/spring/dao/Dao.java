package com.bmore.spring.dao;

import java.util.List;

public interface Dao {
	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> c);
}
