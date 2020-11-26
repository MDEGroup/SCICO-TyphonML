package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class PurchaseDetails {
	
private static final Logger logger = LoggerFactory.getLogger(PurchaseDetails.class);

	private List<Customer> custormerObj; 
	private List<Integer
	> custormer; 

	private List<Inventory> inventoriesObj; 
	private List<Integer
	> inventories; 

		
	public List<Integer
	> getCustormer(){
		return custormer;
	}
	public void setCustormer(List<Integer
	> custormer){
		this.custormer = custormer;
	}
	public List<Integer
	> getInventories(){
		return inventories;
	}
	public void setInventories(List<Integer
	> inventories){
		this.inventories = inventories;
	}


	public List<Customer> getCustomerObj() {
		CustomerService customerService = new CustomerService("http://localhost:8092");
		List<Customer> result = new ArrayList<Customer>();
		for (Integer
		 typeObj : custormer) {
			try {
				result.add(customerService.findById(typeObj));
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	
	public List<Inventory> getInventoryObj() {
		InventoryService inventoryService = new InventoryService("http://localhost:8094");
		List<Inventory> result = new ArrayList<Inventory>();
		for (Integer
		 typeObj : inventories) {
			try {
				result.add(inventoryService.findById(typeObj));
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	
}

