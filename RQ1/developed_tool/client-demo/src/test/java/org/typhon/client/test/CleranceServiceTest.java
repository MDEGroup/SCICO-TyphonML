package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class CleranceServiceTest {
	CleranceService cleranceService;

	@Before
	public void init() {
		cleranceService = new CleranceService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateClerance() {
		Clerance objToCreate = new Clerance();
		//TODO set the objToCreate attributes
		
		cleranceService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(cleranceService.findAll(1,5,"ASC"));
		PagedResources<Clerance> v = cleranceService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
