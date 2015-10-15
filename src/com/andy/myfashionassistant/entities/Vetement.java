package com.andy.myfashionassistant.entities;

public class Vetement {
	private int id;
	private String title;
	private String type;
	private byte[] logo;

	public Vetement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vetement(int id, String name, String type, byte[] logo) {
		super();
		this.id = id;
		this.setTitle(name);
		this.type = type;
		this.logo = logo;
	}

	public Vetement(String name, String type, byte[] logo) {
		super();
		this.setTitle(name);
		this.type = type;
		this.logo = logo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
