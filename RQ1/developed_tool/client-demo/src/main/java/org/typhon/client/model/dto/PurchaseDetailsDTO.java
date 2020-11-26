package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDetailsDTO {

	private List<Integer
	> custormerObj; 
	private List<Integer
	> inventoriesObj; 
		
	public List<Integer
	> getCustormer(){
		return custormerObj;
	}
	public void setCustormer(List<Integer
	> custormerObj){
		this.custormerObj = custormerObj;
	}
	public List<Integer
	> getInventories(){
		return inventoriesObj;
	}
	public void setInventories(List<Integer
	> inventoriesObj){
		this.inventoriesObj = inventoriesObj;
	}

}
