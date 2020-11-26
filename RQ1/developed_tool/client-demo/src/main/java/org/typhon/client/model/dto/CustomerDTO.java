package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {

	private String oid;
	private String name;
	private List<String
	> detailsObj; 
		
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

	public List<String
	> getDetails(){
		return detailsObj;
	}
	public void setDetails(List<String
	> detailsObj){
		this.detailsObj = detailsObj;
	}

}
