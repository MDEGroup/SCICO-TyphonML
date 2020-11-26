package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class PurchaseDetailsServiceTest {
	PurchaseDetailsService purchasedetailsService;
	CustomerService custormerService;
	InventoryService inventoriesService;

	@Before
	public void init() {
		purchasedetailsService = new PurchaseDetailsService("http://localhost:8080");
		custormerService = new CustomerService("http://localhost:8080");
		inventoriesService = new InventoryService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreatePurchaseDetails() {
		PurchaseDetails objToCreate = new PurchaseDetails();
		//TODO set the objToCreate attributes
		
		purchasedetailsService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(purchasedetailsService.findAll(1,5,"ASC"));
		PagedResources<PurchaseDetails> v = purchasedetailsService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
