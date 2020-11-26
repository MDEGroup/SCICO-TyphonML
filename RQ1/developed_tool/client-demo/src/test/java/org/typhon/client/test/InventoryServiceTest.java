package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class InventoryServiceTest {
	InventoryService inventoryService;

	@Before
	public void init() {
		inventoryService = new InventoryService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateInventory() {
		Inventory objToCreate = new Inventory();
		//TODO set the objToCreate attributes
		
		inventoryService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(inventoryService.findAll(1,5,"ASC"));
		PagedResources<Inventory> v = inventoryService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
