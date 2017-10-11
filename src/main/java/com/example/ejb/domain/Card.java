package com.example.ejb.domain;



public class Card {

	private String name;	
	private int code;
	private String pathImage;
	
	
	
	public Card(String name, int code, String pathImage) {
		super();
		this.name = name.replace('_', ' ');		
		this.code = code;
		this.pathImage = pathImage+".png";
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	
	
	
	
	
	
}
