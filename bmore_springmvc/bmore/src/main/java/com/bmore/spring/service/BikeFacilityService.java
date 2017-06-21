package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.Accessibility;
import com.bmore.spring.model.BikeFacility;

public interface BikeFacilityService {
	
	public void persist(BikeFacility g);
	
	public List<BikeFacility> getAll();
}
