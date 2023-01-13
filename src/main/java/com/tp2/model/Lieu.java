package com.tp2.model;

public class Lieu {
	protected int id;
	protected String adresse;
	protected String ville;
	
	public Lieu() {
	}
	
	public Lieu(String adresse, String ville) {
		super();
		this.adresse = adresse;
		this.ville=ville;
	}
	
	public Lieu(int id, String adresse, String ville) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.ville=ville;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
}