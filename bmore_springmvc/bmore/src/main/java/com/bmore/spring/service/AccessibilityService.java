package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.Accessibility;


public interface AccessibilityService {

	public void persist(Accessibility g);
	
	public List<Accessibility> getAll();
}
