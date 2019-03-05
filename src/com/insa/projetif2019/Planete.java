package com.insa.projetif2019;

public class Planete extends Astre {
	private String nom;
	private double rayon;
	private double pesanteur;
	private double perioderevolution;
	private double temperature;
	double masse;
	String type;
	private int[][][] grilleDeNiveau;

	public Planete (double r, double p, double t, double m, String n, String ty,int [][][] grille) {
		rayon =r;
		perioderevolution=p;
		temperature=t;
		masse=m;
		pesanteur=calculpesanteur();	
	 	nom=n;
	 	type=ty;
	 	grilleDeNiveau = grille;
	}
	
	public double calculpesanteur(){
		double p=(6.67*Math.pow(10,-11)*masse)/Math.pow(rayon*Math.pow(10,3), 2);
		return p;
	}
	
	public String info1() {
		String info1;
		info1="Bienvenue sur "+nom+", je suis ravie de te rencontrer. \n On dit de moi que je suis une planète de type"+type;
		if (type=="Tellurique"){
			info1=info1+", c'est à dire que je suis composée essentiellement de roches et de métal qui possède trois enveloppes "
					+ "concentriques (noyau, manteau et croûte). \n Ma surface est solide et composée principalement de matériaux"
					+ " non volatils tels que des roches silicatées et du fer métallique\n ";
		}
		if (type=="gazeuse"){
			info1=info1+",c'est à dire que que je suis composée essentiellement de gaz légers, c’est-à-dire d’hydrogène et d’hélium.\n ";
		}
					
		if (type==" de glace"){
			info1=info1+",c'est à dire que je ne suis pas principalement constituée d'hydrogène et d'hélium, mais de composés volatils"
					+ " tels que l'eau, le méthane ou l'ammoniac\n ";
		}
		
		return info1;	
	}
	public String info2() {
		String info2="";
		if (type=="Tellurique"){
			info2=info2+"Je suis malheureusement plus petite que mes voisines,"
					+ "mon rayon n'est que de "+rayon+ "mais je ne suis pas triste, tout ce qui est petit est mignon.\n ";
		}else{
			info2=info2+ " Je porte aussi le jolie attribut de géante, et oui mon rayon est de"+ rayon+ "km \n ";
		}
		
		return info2;	
	}
	
	public String info3() {
		String info="";
		info="Bienvenue sur "+nom+", je suis ravie de te rencontrer. \n On dit de moi que je suis une planète de type"+type;
		if (type=="Tellurique"){
			info=info+", c'est à dire que je suis composée essentiellement de roches et de métal qui possède trois enveloppes "
					+ "concentriques (noyau, manteau et croûte). \n Ma surface est solide et composée principalement de matériaux"
					+ " non volatils tels que des roches silicatées et du fer métallique. \n Je suis malheureusement plus petite que mes voisines,"
					+ "mon rayon n'est que de "+rayon+ "mais je ne suis pas triste, tout ce qui est petit est mignon.\n  Le mystère est mon"
							+ "maître mot, soit il fait froide soit il fait chaud, je suis à"+temperature+".\n ";
		}
		if (type=="gazeuse"){
			info=info+",c'est à dire que que je suis composée essentiellement de gaz légers, c’est-à-dire d’hydrogène et d’hélium.\n "
					+ " Je porte aussi le jolie attribut de géante, et oui mon rayon est de"+ rayon+ "km\n "
					+ "Je suis vraiment désolée mais chez moi il fait assez froid:"+temperature+"°C; mais ne t'en fait pas j'ai "
					+ "plein d'amour pour te réchauffer.\n ";
		}
		if (type==" de glace"){
			info=info+",c'est à dire que je ne suis pas principalement constituée d'hydrogène et d'hélium, mais de composés volatils"
					+ " tels que l'eau, le méthane ou l'ammoniac\n . Je porte aussi le jolie attribut de géante, et oui mon rayon est de"+ rayon+ "km"
					+ "\n Je suis vraiment désolée mais chez moi il fait assez froid:"+temperature+"°C; mais ne t'en fait pas j'ai "
							+ "plein d'amour pour te réchauffer.\n ";
		}
		info=info+"je fais un tour autour du soleil en "+perioderevolution+".Tu penses pouvoir me battre.\n ";
		if (pesanteur<10){
			info=info+"Youpi!!! Tu es tout leger chez moi, tu veux connaître ton poids, clic sur le bouton\n ";
		} else {
			info=info+"Oulalallala tu as trop mangé aujourd'hui, tu veux connaître ton poids, clic sur le bouton\n ";
		}
		
		return info;	
	}
	public String info4() {
		String info;
		info="Bienvenue sur "+nom+", je suis ravie de te rencontrer. \n On dit de moi que je suis une planète de type"+type;
		if (type=="Tellurique"){
			info=info+", c'est à dire que je suis composée essentiellement de roches et de métal qui possède trois enveloppes "
					+ "concentriques (noyau, manteau et croûte). \n Ma surface est solide et composée principalement de matériaux"
					+ " non volatils tels que des roches silicatées et du fer métallique. \n Je suis malheureusement plus petite que mes voisines,"
					+ "mon rayon n'est que de "+rayon+ "mais je ne suis pas triste, tout ce qui est petit est mignon.\n  Le mystère est mon"
							+ "maître mot, soit il fait froide soit il fait chaud, je suis à"+temperature+".\n ";
		}
		if (type=="gazeuse"){
			info=info+",c'est à dire que que je suis composée essentiellement de gaz légers, c’est-à-dire d’hydrogène et d’hélium.\n "
					+ " Je porte aussi le jolie attribut de géante, et oui mon rayon est de"+ rayon+ "km\n "
					+ "Je suis vraiment désolée mais chez moi il fait assez froid:"+temperature+"°C; mais ne t'en fait pas j'ai "
					+ "plein d'amour pour te réchauffer.\n ";
		}
		if (type==" de glace"){
			info=info+",c'est à dire que je ne suis pas principalement constituée d'hydrogène et d'hélium, mais de composés volatils"
					+ " tels que l'eau, le méthane ou l'ammoniac\n . Je porte aussi le jolie attribut de géante, et oui mon rayon est de"+ rayon+ "km"
					+ "\n Je suis vraiment désolée mais chez moi il fait assez froid:"+temperature+"°C; mais ne t'en fait pas j'ai "
							+ "plein d'amour pour te réchauffer.\n ";
		}
		info=info+"je fais un tour autour du soleil en "+perioderevolution+".Tu penses pouvoir me battre.\n ";
		if (pesanteur<10){
			info=info+"Youpi!!! Tu es tout leger chez moi, tu veux connaître ton poids, clic sur le bouton\n ";
		} else {
			info=info+"Oulalallala tu as trop mangé aujourd'hui, tu veux connaître ton poids, clic sur le bouton\n ";
		}
		
		return info;	
	}
	
	

}
