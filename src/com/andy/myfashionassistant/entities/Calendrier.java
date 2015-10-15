package com.andy.myfashionassistant.entities;

public class Calendrier {
	
	private int id ;
	private byte[] photo ;
	private String texte ;
	public Calendrier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Calendrier(int id, byte[] photo, String texte) {
		super();
		this.id = id;
		this.photo = photo;
		this.texte = texte;
	}
	public Calendrier( byte[] photo, String texte) {
		super();
		
		this.photo = photo;
		this.texte = texte;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	
	
	
	

	

}
