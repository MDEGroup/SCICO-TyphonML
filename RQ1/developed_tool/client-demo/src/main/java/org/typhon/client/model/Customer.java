package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class Customer {
	
private static final Logger logger = LoggerFactory.getLogger(Customer.class);

	private String oid;
	private String name;
	private List<CustomerDetails> detailsObj; 
	private List<String
	> details; 

		
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
		return details;
	}
	public void setDetails(List<String
	> details){
		this.details = details;
	}


	public List<CustomerDetails> getCustomerDetailsObj() {
		CustomerDetailsService customerdetailsService = new CustomerDetailsService("http://localhost:8093");
		List<CustomerDetails> result = new ArrayList<CustomerDetails>();
		for (String
		 typeObj : details) {
			try {
				result.add(customerdetailsService.findById(typeObj));
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	
}

