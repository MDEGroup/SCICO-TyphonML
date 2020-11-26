package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class CleranceDTO {

	private String name;
	private String key;
	private float clarence;
		
	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setKey (String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	public void setClarence (float clarence){
		this.clarence = clarence;
	}

	public float getClarence(){
		return clarence;
	}


}
