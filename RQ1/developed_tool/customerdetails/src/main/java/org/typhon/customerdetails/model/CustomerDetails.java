package org.typhon.customerdetails.model;

import java.util.*;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class CustomerDetails {

	@Id
	private String id;

	private String oid;
	private String name;
	private String surname;
	private String city;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOid(String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	public void setSurname(String surname){
		this.surname = surname;
	}

	public String getSurname(){
		return surname;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

}

