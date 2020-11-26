package org.typhon.song.model;

import java.util.*;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Song {

	@Id
	private String id;

	private String name;
	private String oid;
	private String title;
	private int year;
	private String artist;
	private String artist_id;

	private List<Integer> tracks; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	public void setOid(String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}
	public void setArtist(String artist){
		this.artist = artist;
	}

	public String getArtist(){
		return artist;
	}
	public void setArtist_id(String artist_id){
		this.artist_id = artist_id;
	}

	public String getArtist_id(){
		return artist_id;
	}

	public void setTracks(List<Integer> tracks){
		this.tracks = tracks;
	}

	public List<Integer> getTracks(){
		return tracks;
	}
}

