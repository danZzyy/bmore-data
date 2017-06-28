package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.CustomLocDao;
import com.bmore.spring.model.CustomLocation;

@Service("customLocService")
public class CustomLocServiceImpl implements CustomLocService {

	@Autowired
	CustomLocDao CLDao;
	
	@Override
	public CustomLocation findById(int id) {
		return (CustomLocation) CLDao.getById(id);
	}

	@Override
	public void saveLoc(CustomLocation loc) {
		CLDao.persist(loc);
	}

	@Override
	public void updateLoc(CustomLocation loc) {
		CLDao.update(loc);
	}

	@Override
	public void deleteItem(CustomLocation loc) {
		CLDao.deleteItem(loc);
	}

	@Override
	public List<CustomLocation> findAll() {
		return CLDao.getAll(CustomLocation.class);
	}

	@Override
	public boolean exists(CustomLocation loc) {
		return findById(loc.getId()) != null;
	}

}
