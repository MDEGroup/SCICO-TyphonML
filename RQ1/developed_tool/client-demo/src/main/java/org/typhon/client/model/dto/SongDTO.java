package org.typhon.client.model.dto;

import java.util.*;
import java.sql.Timestamp;
	
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongDTO {

	private String name;
	private String oid;
	private String title;
	private int year;
	private String artist;
	private String artist_id;
	private List<> tracksObj; 
		
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
		return tracksObj;
	}
	public void setTracks(List<> tracksObj){
		this.tracksObj = tracksObj;
	}

}
