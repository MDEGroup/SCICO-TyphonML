	
package org.typhon.client.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.typhon.client.model.*;
import org.typhon.client.service.*;

public class RelationTest {

	CleranceService cleranceService;
	CustomerService customerService;
	CustomerDetailsService customerdetailsService;
	InventoryService inventoryService;
	PurchaseDetailsService purchasedetailsService;
	SimilarityService similarityService;
	SongService songService;
	TrackService trackService;

	@Before
	public void init() {
		cleranceService  = new CleranceService("http://localhost:8091");
		customerService  = new CustomerService("http://localhost:8092");
		customerdetailsService  = new CustomerDetailsService("http://localhost:8093");
		inventoryService  = new InventoryService("http://localhost:8094");
		purchasedetailsService  = new PurchaseDetailsService("http://localhost:8095");
		similarityService  = new SimilarityService("http://localhost:8096");
		songService  = new SongService("http://localhost:8097");
		trackService  = new TrackService("http://localhost:8098");
	}
	
	@Test
	public void testFindCleranceById() {
		Clerance p = cleranceService.findById();
	}
	@Test
	public void testFindCustomerById() {
		Customer p = customerService.findById(1
		);
		List<CustomerDetails> details = p.getCustomerDetailsObj();
		System.out.println(String.format("The Customer is related with CustomerDetails:"));
		for (CustomerDetails iter : details) {
				System.out.println("\t- " + iter.getOid());
				System.out.println("\t- " + iter.getName());
				System.out.println("\t- " + iter.getSurname());
				System.out.println("\t- " + iter.getCity());
		}	
	}
	@Test
	public void testFindCustomerDetailsById() {
		CustomerDetails p = customerdetailsService.findById("1"
		);
	}
	@Test
	public void testFindInventoryById() {
		Inventory p = inventoryService.findById(1
		);
	}
	@Test
	public void testFindPurchaseDetailsById() {
		PurchaseDetails p = purchasedetailsService.findById(1
		);
		List<Customer> custormer = p.getCustomerObj();
		System.out.println(String.format("The PurchaseDetails is related with Customer:"));
		for (Customer iter : custormer) {
				System.out.println("\t- " + iter.getOid());
				System.out.println("\t- " + iter.getName());
		}	
		List<Inventory> inventories = p.getInventoryObj();
		System.out.println(String.format("The PurchaseDetails is related with Inventory:"));
		for (Inventory iter : inventories) {
				System.out.println("\t- " + iter.getOid());
				System.out.println("\t- " + iter.getBand());
				System.out.println("\t- " + iter.getName());
		}	
	}
	@Test
	public void testFindSimilarityById() {
		Similarity p = similarityService.findById();
		List<Song> source = p.getSongObj();
		System.out.println(String.format("The Similarity is related with Song:"));
		for (Song iter : source) {
				System.out.println("\t- " + iter.getName());
				System.out.println("\t- " + iter.getOid());
				System.out.println("\t- " + iter.getTitle());
				System.out.println("\t- " + iter.getYear());
				System.out.println("\t- " + iter.getArtist());
				System.out.println("\t- " + iter.getArtist_id());
		}	
		List<Song> target = p.getSongObj();
		System.out.println(String.format("The Similarity is related with Song:"));
		for (Song iter : target) {
				System.out.println("\t- " + iter.getName());
				System.out.println("\t- " + iter.getOid());
				System.out.println("\t- " + iter.getTitle());
				System.out.println("\t- " + iter.getYear());
				System.out.println("\t- " + iter.getArtist());
				System.out.println("\t- " + iter.getArtist_id());
		}	
	}
	@Test
	public void testFindSongById() {
		Song p = songService.findById("1"
		);
		List<Track> tracks = p.getTrackObj();
		System.out.println(String.format("The Song is related with Track:"));
		for (Track iter : tracks) {
				System.out.println("\t- " + iter.getOid());
				System.out.println("\t- " + iter.getNumber());
				System.out.println("\t- " + iter.getLength());
				System.out.println("\t- " + iter.getName());
		}	
	}
	@Test
	public void testFindTrackById() {
		Track p = trackService.findById();
	}
}
