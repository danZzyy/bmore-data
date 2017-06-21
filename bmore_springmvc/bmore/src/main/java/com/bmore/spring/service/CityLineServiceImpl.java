package com.bmore.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmore.spring.dao.Dao;
import com.bmore.spring.model.CityLine;

@Service("cityLineService")
public class CityLineServiceImpl implements CityLineService {

	@Autowired
	Dao dao;
	@Override
	public void persist(CityLine a) {
		dao.persist(a);

	}

	@Override
	public List<CityLine> getAll() {
		return dao.getAll(CityLine.class);
	}
}
