package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class Clerance {
	
private static final Logger logger = LoggerFactory.getLogger(Clerance.class);

	private String name;
	private String key;
	private float clarence;
		
	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setKey (String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	public void setClarence (float clarence){
		this.clarence = clarence;
	}

	public float getClarence(){
		return clarence;
	}



}

