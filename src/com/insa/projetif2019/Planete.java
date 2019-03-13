package com.insa.projetif2019;

public class Planete {
	private String nom;
	private double rayon;
	private double pesanteur;
	private double perioderevolution;
	private double temperaturemin;
	private double temperaturemax;
	double masse;
	String type;
	//private Bloc[][] grilleDeNiveau;

	public Planete (double r, double p, double tm, double tmx,double m, String n, String ty) {
		rayon =r;
		perioderevolution=p;
		temperaturemin=tm;
		temperaturemax=tmx;
		masse=m;
		pesanteur=calculpesanteur();	
	 	nom=n;
	 	type=ty;
	 	//grilleDeNiveau = generergrille();
	}
	/*public Bloc [][] generergrille(){
		Bloc [][ grille=new Bloc[][];
		return grille;
	}*/
		
	public double calculpesanteur(){
		double p=(6.67*Math.pow(10,-11)*masse)/Math.pow(rayon*Math.pow(10,3), 2);
		return p;
	}
	
	public String info1() {
		String info1;
		info1="Bonjour à toi je suis "+nom+", ravie de te rencontrer. \n On dit de moi que je suis une planète de type"+type;
		if (type=="Tellurique"){
			info1=info1+", c'est a dire que je suis composee essentiellement de roches et de métal qui possede trois enveloppes "
					+ "concentriques (noyau, manteau et croûte). \n Ma surface est solide et composee principalement de matériaux"
					+ " non volatils tels que des roches silicatees et du fer metallique\n ";
		}
		if (type=="gazeuse"){
			info1=info1+",c'est a dire que que je suis composee essentiellement de gaz legers, c’est-a-dire d’hydrogene et d’helium.\n ";
		}
					
		if (type==" de glace"){
			info1=info1+",c'est a dire que je ne suis pas principalement constituee d'hydrogene et d'helium, mais de composes volatils"
					+ " tels que l'eau, le methane ou l'ammoniac\n ";
		}
		
		return info1;	
	}
	public String info2() {
		String info2;
		if (type=="Tellurique"){
			info2="Je suis malheureusement plus petite que mes voisines,"
					+ "mon rayon n'est que de "+rayon+ "km mais je ne suis pas triste, tout ce qui est petit est mignon.\n ";
		}else{
			info2=" Je porte aussi le jolie attribut de geante, et oui mon rayon est de"+ rayon+ "km \n ";
		}
		
		return info2;	
	}
	
	public String info3() {
		String info3;
		if (type=="Tellurique"){
			info3= "Le mystère est mon maître mot, soit il fait froide soit il fait chaud, je varie de "+temperaturemin+"°C à " +temperaturemax+"°C.\n ";
		}
		else {
			info3="Je suis vraiment desolee mais chez moi il fait assez froid:je varie de"+temperaturemin+"°C à" +temperaturemax+"°C; mais ne t'en fait pas j'ai "
					+ "plein d'amour pour te réchauffer.\n ";
		}
		return info3;	
	}
	
	public String info4() {
		String info4;
		info4="je fais un tour autour du soleil en "+perioderevolution+" jours .Tu penses pouvoir me battre.\n ";
		if (pesanteur<10){
			info4="Youpi!!! Tu es tout leger chez moi, tu veux connaître ton poids, clic sur le bouton\n ";
		} else {
			info4="Oulalallala tu as trop mangé aujourd'hui, tu veux connaître ton poids, clic sur le bouton\n ";
		}
		
		return info4;	
	}
	
	

}
