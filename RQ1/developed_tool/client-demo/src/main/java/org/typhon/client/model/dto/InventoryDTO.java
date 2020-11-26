package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryDTO {

	private String oid;
	private String band;
	private String name;
		
	public void setOid (String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	public void setBand (String band){
		this.band = band;
	}

	public String getBand(){
		return band;
	}

	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}


}
