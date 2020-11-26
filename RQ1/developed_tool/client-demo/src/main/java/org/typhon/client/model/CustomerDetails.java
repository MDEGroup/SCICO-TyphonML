package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class CustomerDetails {
	
private static final Logger logger = LoggerFactory.getLogger(CustomerDetails.class);

	private String oid;
	private String name;
	private String surname;
	private String city;
		
	public void setOid (String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSurname (String surname){
		this.surname = surname;
	}

	public String getSurname(){
		return surname;
	}

	public void setCity (String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}



}

