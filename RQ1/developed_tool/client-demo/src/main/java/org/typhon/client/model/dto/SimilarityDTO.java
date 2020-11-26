package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimilarityDTO {

	private String name;
	private float values;
	private List<String
	> sourceObj; 
	private List<String
	> targetObj; 
		
	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValues (float values){
		this.values = values;
	}

	public float getValues(){
		return values;
	}

	public List<String
	> getSource(){
		return sourceObj;
	}
	public void setSource(List<String
	> sourceObj){
		this.sourceObj = sourceObj;
	}
	public List<String
	> getTarget(){
		return targetObj;
	}
	public void setTarget(List<String
	> targetObj){
		this.targetObj = targetObj;
	}

}
