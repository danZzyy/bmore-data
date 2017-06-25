package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.Dao;
import com.bmore.spring.model.Accessibility;
import com.bmore.spring.model.BikeFacility;

@Service("bikeFacilityService")
public class BikeFacilityServiceImpl implements BikeFacilityService {

	@Autowired
	Dao dao;
	
	@Override
	public void persist(BikeFacility a) {
		dao.persist(a);

	}

	@Override
	public List<BikeFacility> getAll() {
		return dao.getAll(BikeFacility.class);
	}

}
