package com.bmore.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;

import com.vividsolutions.jts.geom.MultiLineString;

@Entity(name="trail")
@Table(name="trail")
public class Trail implements geoJSONable{

	@Id
	private int id;
	
	private String geom;
	
	@Override
	public String toJSON() {
		
		String JSON = "{\"type\": \"Feature\", \"geometry\": {\"type\": \"MultiLineString\", \"coordinates\": %s } }";
		
		return String.format(JSON, this.geom);
	}

	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="geom")
	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

}
