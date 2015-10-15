package com.andy.myfashionassistant.entities;

public class shopping {
	private int iconId;
	private String clothesType ;
	public shopping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public shopping(int iconId, String clothesType) {
		super();
		this.iconId = iconId;
		this.clothesType = clothesType;
	}
	public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	public String getClothesType() {
		return clothesType;
	}
	public void setClothesType(String clothesType) {
		this.clothesType = clothesType;
	}
	

}
