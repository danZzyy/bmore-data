package com.bmore.spring.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name="custom_location")
@Table(name="custom_location")
public class CustomLocation implements geoJSONable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6531376693976340794L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String address;
	
	private String name;
	
	private double lat;
	
	private double lng;
	
	private String accessibility;
	
	@Override
	public String toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("type", "Feature");
		JSONObject properties = new JSONObject();
		JSONObject geometry = new JSONObject();
		
		properties.put("name", this.name);
		properties.put("address", this.address);
		properties.put("accessibility", this.accessibility);
		geometry.put("type", "Point");
		JSONArray coordinates = new JSONArray();
		coordinates.add(this.lng);
		coordinates.add(this.lat);
		
		geometry.put("coordinates", coordinates);
		obj.put("properties", properties);
		obj.put("geometry", geometry);
		
		return obj.toJSONString();
	}
	
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="lat")
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Column(name="lng")
	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}


}
