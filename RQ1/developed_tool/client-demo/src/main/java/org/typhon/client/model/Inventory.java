package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class Inventory {
	
private static final Logger logger = LoggerFactory.getLogger(Inventory.class);

	private String oid;
	private String band;
	private String name;
		
	public void setOid (String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	public void setBand (String band){
		this.band = band;
	}

	public String getBand(){
		return band;
	}

	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}



}

