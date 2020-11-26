package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class CustomerServiceTest {
	CustomerService customerService;
	CustomerDetailsService detailsService;

	@Before
	public void init() {
		customerService = new CustomerService("http://localhost:8080");
		detailsService = new CustomerDetailsService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateCustomer() {
		Customer objToCreate = new Customer();
		//TODO set the objToCreate attributes
		
		customerService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(customerService.findAll(1,5,"ASC"));
		PagedResources<Customer> v = customerService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
