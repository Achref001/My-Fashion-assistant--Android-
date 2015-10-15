package com.andy.myfashionassistant.entities;

public class Assistance {
	private int id;
	private byte[] logoCoat;
	private byte[] logoUpperBody;
	private byte[] logoLowerBody;
	private byte[] logoShoes;
	private byte[] logoAccessories;
	private byte[] logoDress;
	private String dataAssistance;
	public Assistance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Assistance(int id, byte[] logoCoat, byte[] logoUpperBody,
			byte[] logoLowerBody, byte[] logoShoes, byte[] logoAccessories,
			byte[] logoDress, String dataAssistance) {
		super();
		this.id = id;
		this.logoCoat = logoCoat;
		this.logoUpperBody = logoUpperBody;
		this.logoLowerBody = logoLowerBody;
		this.logoShoes = logoShoes;
		this.logoAccessories = logoAccessories;
		this.logoDress = logoDress;
		this.dataAssistance = dataAssistance;
	}
	
	public Assistance(byte[] logoCoat, byte[] logoUpperBody,
			byte[] logoLowerBody, byte[] logoShoes, byte[] logoAccessories,
			byte[] logoDress, String dataAssistance) {
		super();
		this.logoCoat = logoCoat;
		this.logoUpperBody = logoUpperBody;
		this.logoLowerBody = logoLowerBody;
		this.logoShoes = logoShoes;
		this.logoAccessories = logoAccessories;
		this.logoDress = logoDress;
		this.dataAssistance = dataAssistance;
	}
	public Assistance(byte[] logoCoat, byte[] logoUpperBody,
			byte[] logoLowerBody, byte[] logoShoes, byte[] logoAccessories,byte[] logoDress)
	{
		super();
		
		this.logoCoat = logoCoat;
		this.logoUpperBody = logoUpperBody;
		this.logoLowerBody = logoLowerBody;
		this.logoShoes = logoShoes;
		this.logoAccessories = logoAccessories;
		this.logoDress = logoDress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getLogoCoat() {
		return logoCoat;
	}
	public void setLogoCoat(byte[] logoCoat) {
		this.logoCoat = logoCoat;
	}
	public byte[] getLogoUpperBody() {
		return logoUpperBody;
	}
	public void setLogoUpperBody(byte[] logoUpperBody) {
		this.logoUpperBody = logoUpperBody;
	}
	public byte[] getLogoLowerBody() {
		return logoLowerBody;
	}
	public void setLogoLowerBody(byte[] logoLowerBody) {
		this.logoLowerBody = logoLowerBody;
	}
	public byte[] getLogoShoes() {
		return logoShoes;
	}
	public void setLogoShoes(byte[] logoShoes) {
		this.logoShoes = logoShoes;
	}
	public byte[] getLogoAccessories() {
		return logoAccessories;
	}
	public void setLogoAccessories(byte[] logoAccessories) {
		this.logoAccessories = logoAccessories;
	}
	public byte[] getLogoDress() {
		return logoDress;
	}
	public void setLogoDress(byte[] logoDress) {
		this.logoDress = logoDress;
	}
	public String getDataAssistance() {
		return dataAssistance;
	}
	public void setDataAssistance(String dataAssistance) {
		this.dataAssistance = dataAssistance;
	}

}
