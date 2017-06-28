package com.bmore.spring.dao;

import java.util.List;
/*
 * A read/write only DAO used for data taken from Baltimore city data
 */
public interface Dao {
	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> c);
}
