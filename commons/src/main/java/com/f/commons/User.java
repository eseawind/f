package com.f.commons;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private UType type;
	private long id;
	
	public User(long id,UType type){
		this.type = type;
		this.id = id;
	}
	
	public UType getUType(){
		return type;
	}
	
	public long getId(){
		return id;
	}
	 
}
