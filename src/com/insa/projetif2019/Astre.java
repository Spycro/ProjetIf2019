package com.insa.projetif2019;

public abstract class Astre {
	protected String nom;
	protected double periode;
	protected String genre;

	public Astre () {
		
	}
	public  String getNom() {
		String n=nom;
		return n;
	}
	public abstract String info1();
	public abstract String info2();
	public abstract String info3();
	public abstract String info4();
	public abstract String calculProchaineAp (int date, int age);
	public abstract int calculPoids(int pTerre);
	
	

}
