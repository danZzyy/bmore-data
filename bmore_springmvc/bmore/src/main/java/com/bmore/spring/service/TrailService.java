package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.Trail;

public interface TrailService {

	public void persist(Trail g);
	
	public List<Trail> getAll();
}
