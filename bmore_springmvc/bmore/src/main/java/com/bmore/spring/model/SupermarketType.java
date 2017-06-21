package com.bmore.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="supermarketType")
@Table(name="supermarket_lookup")
public class SupermarketType {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="supermarket_type")
	private String type;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
