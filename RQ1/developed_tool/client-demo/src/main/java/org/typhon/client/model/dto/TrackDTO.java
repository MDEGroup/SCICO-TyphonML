package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackDTO {

	private String oid;
	private int number;
	private int length;
	private int name;
		
	public void setOid (String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	public void setNumber (int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setLength (int length){
		this.length = length;
	}

	public int getLength(){
		return length;
	}

	public void setName (int name){
		this.name = name;
	}

	public int getName(){
		return name;
	}


}
