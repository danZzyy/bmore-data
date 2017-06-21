package com.bmore.spring.service;

import java.util.List;

import com.bmore.spring.model.CityLine;

public interface CityLineService {

	public void persist(CityLine g);
	
	public List<CityLine> getAll();

}
