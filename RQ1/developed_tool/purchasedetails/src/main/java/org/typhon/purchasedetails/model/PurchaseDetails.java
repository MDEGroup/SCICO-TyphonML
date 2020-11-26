package org.typhon.purchasedetails.model;

import java.util.*;
import java.sql.Timestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PurchaseDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@ElementCollection
	private List<String> custormer; 
	@ElementCollection
	private List<String> inventories; 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		

	public void setCustormer(List<String> custormer){
		this.custormer = custormer;
	}

	public List<String> getCustormer(){
		return custormer;
	}

	public void setInventories(List<String> inventories){
		this.inventories = inventories;
	}

	public List<String> getInventories(){
		return inventories;
	}
}

