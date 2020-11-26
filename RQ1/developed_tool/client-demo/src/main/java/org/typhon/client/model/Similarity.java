package org.typhon.client.model;

import java.util.*;
import java.sql.Timestamp;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typhon.client.service.*;

	
public class Similarity {
	
private static final Logger logger = LoggerFactory.getLogger(Similarity.class);

	private String name;
	private float values;
	private List<Song> sourceObj; 
	private List<String
	> source; 

	private List<Song> targetObj; 
	private List<String
	> target; 

		
	public void setName (String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValues (float values){
		this.values = values;
	}

	public float getValues(){
		return values;
	}

	public List<String
	> getSource(){
		return source;
	}
	public void setSource(List<String
	> source){
		this.source = source;
	}
	public List<String
	> getTarget(){
		return target;
	}
	public void setTarget(List<String
	> target){
		this.target = target;
	}


	public List<Song> getSongObj() {
		SongService songService = new SongService("http://localhost:8097");
		List<Song> result = new ArrayList<Song>();
		for (String
		 typeObj : source) {
			try {
				result.add(songService.findById(typeObj));
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	
	public List<Song> getSongObj() {
		SongService songService = new SongService("http://localhost:8097");
		List<Song> result = new ArrayList<Song>();
		for (String
		 typeObj : target) {
			try {
				result.add(songService.findById(typeObj));
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	
}

