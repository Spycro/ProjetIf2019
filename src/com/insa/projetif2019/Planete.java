package com.insa.projetif2019;

public class Planete extends Astre {
	private double rayon;
	private double pesanteur;
	private double temperatureMin;
	private double temperatureMax;
	double masse;
	String type;
	//private Bloc[][] grilleDeNiveau;

	public Planete (double r, double p, double tm, double tmx,double m, String n, String ty) {
		genre="Planete";
		rayon =r;
		periode=p;
		temperatureMin=tm;
		temperatureMax=tmx;
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
		
	public  String calculProchaineAp(int date, int age) {
		return null;
	}
	
	public double calculpesanteur(){
		double p=(6.67*Math.pow(10,-11)*masse)/Math.pow(rayon*Math.pow(10,3), 2);
		return p;
	}
	public int calculPoids(int pTerre){
		int poid=(int)((pTerre/9.8)*pesanteur);
		return poid;
	}
	public String info1() {
		String info1;
		info1="Bonjour a toi je suis "+nom+", ravie de te rencontrer. \n On dit de moi que je suis une planete de type"+type;
		if (type=="Tellurique"){
			info1=info1+", c est a dire que je suis composee essentiellement de roches et de metal qui possede trois enveloppes "
					+ "concentriques (noyau, manteau et croute). \n Ma surface est solide et composee principalement de materiaux"
					+ " non volatils tels que des roches silicatees et du fer metallique \n ";
		}
		if (type=="gazeuse"){
			info1=info1+",c est a dire que que je suis composee essentiellement de gaz legers comme l hydrogene et l helium.\n ";
		}
					
		if (type==" de glace"){
			info1=info1+",c'est a dire que je ne suis pas principalement constituee d hydrogene et d helium, mais de composes volatils"
					+ " tels que l'eau, le methane ou l'ammoniac\n ";
		}
		info1=info1+"\n Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info1;	
	}
	public String info2() {
		String info2;
		if (type=="Tellurique"){
			info2="Je suis malheureusement plus petite que mes voisines,"
					+ " mon rayon n'est que de "+rayon+ "km mais je ne suis pas triste, tout ce qui est petit est mignon.\n ";
		}else{
			info2=" Je porte aussi le jolie attribut de geante, et oui mon rayon est de"+ rayon+ "km \n ";
		}
		info2=info2+"\n Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info2;	
	}
	
	public String info3() {
		String info3;
		if (type=="Tellurique"){
			info3= "Le mystere est mon maitre mot, soit il fait froide soit il fait chaud, je varie de "+temperatureMin+" C a " +temperatureMax+"C.\n ";
		}
		else {
			info3="Je suis vraiment desolee mais chez moi il fait assez froid:je varie de"+temperatureMin+" C a " +temperatureMax+"C; mais ne t'en fait pas j'ai "
					+ "plein d'amour pour te rechauffer.\n ";
		}
		info3=info3+"\n Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info3;	
	}
	
	public String info4() {
		String info4;
		info4="je fais un tour autour du soleil en "+periode+" jours .Tu penses pouvoir me battre.\n ";
		if (pesanteur<10){
			info4="Youpi!!! Tu es tout leger chez moi. Je sais personne n'aime le dire mais assume toi. Renseigne ce qu'indique ta balance "
					+ " dans la zone et clic sur le bouton\n ";
		} else {
			info4="Oulalallala tu as trop mange aujourd'hui, Je sais personne n'aime le dire mais assume toi. Renseigne ce qu'indique ta balance"
					+ " dans la zone et clic sur le bouton\n ";
		}
		info4=info4+"\n Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info4;	
	}
	
	

}
