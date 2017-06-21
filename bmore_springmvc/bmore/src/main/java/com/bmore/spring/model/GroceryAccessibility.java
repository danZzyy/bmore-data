package com.bmore.spring.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name="grocery_accessibility")
@Table(name="grocery_accessibility")
public class GroceryAccessibility implements geoJSONable{

	@Id
	private int id;
	
	private Accessibility accessibility;
	
	private Grocery grocery;

	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Access(AccessType.PROPERTY)
	@OneToOne
	@JoinColumn(name="accessibility")
	public Accessibility getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(Accessibility accessibility) {
		this.accessibility = accessibility;
	}
	


	@Access(AccessType.PROPERTY)
	@OneToOne
	@JoinColumn(name="groc_id")
	public Grocery getGrocery() {
		return grocery;
	}

	public void setGrocery(Grocery grocery) {
		this.grocery = grocery;
	}

	@Override
	public String toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("type", "Feature");
		JSONObject properties = new JSONObject();
		JSONObject geometry = new JSONObject();
		
		properties.put("name", this.grocery.getName());
		properties.put("address", this.grocery.getAddress());
		properties.put("type", this.grocery.getType().getType());
		properties.put("accessibility", this.accessibility.getAccessibility());
		geometry.put("type", "Point");
		JSONArray coordinates = new JSONArray();
		coordinates.add(this.grocery.getLng());
		coordinates.add(this.grocery.getLat());
		
		geometry.put("coordinates", coordinates);
		obj.put("properties", properties);
		obj.put("geometry", geometry);
		
		return obj.toJSONString();
	}

}
