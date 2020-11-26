package org.typhon.inventory.model;

import java.util.*;
import java.sql.Timestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String oid; 
	private String band; 
	private String name; 

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
	public void setBand(String band){
		this.band = band;
	}

	public String getBand (){
		return band;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName (){
		return name;
	}

}

