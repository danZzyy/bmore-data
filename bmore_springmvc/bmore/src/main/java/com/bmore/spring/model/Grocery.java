package com.bmore.spring.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name="grocery")
@Table(name="grocery")
public class Grocery{

	@Id
	@Column(name="groc_id")
	private int grocId;
	
	private String name;
	
	private String address;
	
	private SupermarketType type;
	
	private double lat;
	
	private double lng;

	
	public int getGrocId() {
		return grocId;
	}

	public void setGrocId(int grocId) {
		this.grocId = grocId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="address")	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	@Access(AccessType.PROPERTY)
	@OneToOne
	@JoinColumn(name="type")
	public SupermarketType getType() {
		return type;
	}

	public void setType(SupermarketType type) {
		this.type = type;
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

}
