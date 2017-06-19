package com.bmore.spring.model;

import com.vividsolutions.jts.geom.MultiLineString;

public class Trail implements geoJSONable{

	private int id;
	
	private MultiLineString geom;
	
	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultiLineString getGeom() {
		return geom;
	}

	public void setGeom(MultiLineString geom) {
		this.geom = geom;
	}

}
