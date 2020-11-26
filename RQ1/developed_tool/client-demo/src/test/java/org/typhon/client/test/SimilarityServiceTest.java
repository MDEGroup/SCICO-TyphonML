package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class SimilarityServiceTest {
	SimilarityService similarityService;
	SongService sourceService;
	SongService targetService;

	@Before
	public void init() {
		similarityService = new SimilarityService("http://localhost:8080");
		sourceService = new SongService("http://localhost:8080");
		targetService = new SongService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateSimilarity() {
		Similarity objToCreate = new Similarity();
		//TODO set the objToCreate attributes
		
		similarityService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(similarityService.findAll(1,5,"ASC"));
		PagedResources<Similarity> v = similarityService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
