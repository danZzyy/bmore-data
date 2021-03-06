package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.CustomLocation;

public interface CustomLocService {

	CustomLocation findById(int id);
	
	void saveLoc(CustomLocation loc);
	
	void updateLoc(CustomLocation loc);
	
	void deleteItem(CustomLocation loc);
	
	List<CustomLocation> findAll();
	
	boolean exists(CustomLocation loc);
}
