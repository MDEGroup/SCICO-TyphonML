package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class Track {
	
private static final Logger logger = LoggerFactory.getLogger(Track.class);

	private String oid;
	private int number;
	private int length;
	private int name;
		
	public void setOid (String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	public void setNumber (int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setLength (int length){
		this.length = length;
	}

	public int getLength(){
		return length;
	}

	public void setName (int name){
		this.name = name;
	}

	public int getName(){
		return name;
	}



}

