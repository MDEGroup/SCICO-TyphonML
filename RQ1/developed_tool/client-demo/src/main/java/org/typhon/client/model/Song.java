package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class Song {
	
private static final Logger logger = LoggerFactory.getLogger(Song.class);

	private String name;
	private String oid;
	private String title;
	private int year;
	private String artist;
	private String artist_id;
	private List<Track> tracksObj; 
	private List<> tracks; 

		
	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setOid (String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	public void setTitle (String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setYear (int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setArtist (String artist){
		this.artist = artist;
	}

	public String getArtist(){
		return artist;
	}

	public void setArtist_id (String artist_id){
		this.artist_id = artist_id;
	}

	public String getArtist_id(){
		return artist_id;
	}

	public List<> getTracks(){
		return tracks;
	}
	public void setTracks(List<> tracks){
		this.tracks = tracks;
	}


	public List<Track> getTrackObj() {
		TrackService trackService = new TrackService("http://localhost:8098");
		List<Track> result = new ArrayList<Track>();
		for ( typeObj : tracks) {
			try {
				result.add(trackService.findById(typeObj));
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	
}

