package org.typhon.similarity.model;

import java.util.*;

import org.neo4j.ogm.annotation.NodeEntity;

import java.sql.Timestamp;




@NodeEntity
public class Similarity{
	
	private String name;
	private float value;
	
	private String source;
	private String target;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
}