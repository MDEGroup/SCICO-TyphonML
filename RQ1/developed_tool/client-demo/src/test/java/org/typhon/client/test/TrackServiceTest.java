package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class TrackServiceTest {
	TrackService trackService;

	@Before
	public void init() {
		trackService = new TrackService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateTrack() {
		Track objToCreate = new Track();
		//TODO set the objToCreate attributes
		
		trackService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(trackService.findAll(1,5,"ASC"));
		PagedResources<Track> v = trackService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
