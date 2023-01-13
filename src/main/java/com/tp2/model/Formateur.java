package com.tp2.model;

public class Formateur {
	
	protected int id;
	protected String cin;
	protected String nom;
	protected int age;
	
	public Formateur() {
	}
	
	public Formateur(String cin, String nom, int age) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.age = age;
	}

	public Formateur(int id, String cin, String nom, int age) {
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}

