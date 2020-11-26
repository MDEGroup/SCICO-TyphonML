package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class CustomerDetailsServiceTest {
	CustomerDetailsService customerdetailsService;

	@Before
	public void init() {
		customerdetailsService = new CustomerDetailsService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateCustomerDetails() {
		CustomerDetails objToCreate = new CustomerDetails();
		//TODO set the objToCreate attributes
		
		customerdetailsService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(customerdetailsService.findAll(1,5,"ASC"));
		PagedResources<CustomerDetails> v = customerdetailsService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
