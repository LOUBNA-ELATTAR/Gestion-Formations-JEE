package com.tp2.model;
public class Formation {
	protected int id;
	protected String theme;
	protected String lieu;
	
	public Formation(int id, String theme, String lieu) {
		super();
		this.id = id;
		this.theme = theme;
		this.lieu = lieu;
	}
	public int getId() {
		return id;
	}
	public Formation(String theme, String lieu) {
		super();
		this.theme = theme;
		this.lieu = lieu;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
}