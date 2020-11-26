package org.typhon.customer.model;

import java.util.*;
import java.sql.Timestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String oid; 
	private String name; 

	@ElementCollection
	private List<String> details; 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	public void setOid(String oid){
		this.oid = oid;
	}

	public String getOid (){
		return oid;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName (){
		return name;
	}

	public void setDetails(List<String> details){
		this.details = details;
	}

	public List<String> getDetails(){
		return details;
	}
}

