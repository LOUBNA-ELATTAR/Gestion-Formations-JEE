package com.tp2.model;

public class FF {
	protected int id;
	protected String theme;
	protected String nom;
	
	public FF() {
	}
	
	public FF(String theme, String nom) {
		super();
		this.theme = theme;
		this.nom = nom;
	}

	public FF(int id, String theme, String nom) {
		super();
		this.id = id;
		this.theme = theme;
		this.nom = nom;
	}
	
	public int getId() {
		return id;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}

