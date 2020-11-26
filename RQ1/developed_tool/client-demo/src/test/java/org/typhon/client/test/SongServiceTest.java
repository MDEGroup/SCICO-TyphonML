package org.typhon.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class SongServiceTest {
	SongService songService;
	TrackService tracksService;

	@Before
	public void init() {
		songService = new SongService("http://localhost:8080");
		tracksService = new TrackService("http://localhost:8080");
	}

	//Stub for object creation
	@Test
	public void testCreateSong() {
		Song objToCreate = new Song();
		//TODO set the objToCreate attributes
		
		songService.create(objToCreate);
	}
		
	@Test
	public void testFindAll() {
		assertNotNull(songService.findAll(1,5,"ASC"));
		PagedResources<Song> v = songService.findAll(1,5,"ASC");
		v.getLinks();
	}
	
}

	
