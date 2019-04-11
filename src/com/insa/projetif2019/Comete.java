package com.insa.projetif2019;
import java.util.Scanner;


public class Comete extends Astre  {
	private int anneeAp;
	private int moisAp;
	private int jourAp;
	//private int[][] grilleDeNiveau;

	private String decouvreur;
	public Comete (String n, int a, int m, int j, double p, String d){
		genre="Comete";
		anneeAp=a;
		nom=n;
		decouvreur=d;
		moisAp=m;
		jourAp= j;
		periode=p;
		//grilleDeNiveau = generergrille();
	}
	/*public Bloc [][] generergrille(){
		Bloc [][ grille=new Bloc[][];
		return grille;
	}*/
	
	public String info1 (){
		String info1= "Bonjour jeune explorateur, bienvenue sur "+nom+", je suis une comete.\n C est a dire que je suis composee de trois parties : \n un noyau, une chevelure"
				+ " (je suis certaine qu'elle est plus belle que la tienne) et les queues.\n"
				+ " Mon noyau et ma chevelure constituent ce que les scientifiques appellent ma tete \n";
		info1=info1+" Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info1;	
	}
	public String info2() {

		String info2=decouvreur+"m'a decouvert le "+jourAp+"/"+moisAp+"/"+anneeAp+". Original n'est ce pas";
		info2=info2+" Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info2;
	}
	public String info3() {
		String info3="Ma periode orbitale est de "+periode+"ans,\n"
				+ "cela correspond a la duree que je mets pour revenir au meme point. \n Bon j'avoue, je suis lente mais le principal"
				+ "c'est d'arriver \n"; 
		info3=info3+" Tu as fini de lire, ferme ma fenetre et appuie sur retour";
		return info3;
	}
	public String info4(){
		String info4=". Je suis bien plus vieille que toi tu sais, "
				+ "penses-tu que tu pourras me voir un jour ? \n Verifions, Pour commencer, ton interface preferee a besoin de toi.\n"
				+ "Indique dans le premier cadre, en quelle annee somme nous ? \n Maintenant LA question qui fache, renseigne dans la seconde case ton age et clic sur le bouton \n ";

		return info4;
	}
	

	
	public String calculProchaineAp (int date, int age) {
		String appa;
		int anneepro=0;
		while (anneepro<date) {
			anneepro=(int) (anneepro+periode);
		}
		if ((age+(anneepro-date))<100){
			appa= "Tu auras le grand honneur de m'observer le " +jourAp+"/"+moisAp+"/"+anneepro+" Pour avoir cette chance;"
				+ "tu dois rester en bonne sante: mange 5 fruits et legumes par jour.";
		}else {
			appa= "Sauf si la science a fait de grand progres depuis ma creation, tu n'auras pas le grand honneur de m'observer le " +jourAp+"/"+moisAp+"/"+anneeAp+" Je suis vraiment desolee mais j'aime me faire attendre,"
					+ "tes enfants auront peut �tre plus de chance (ahhhhh ces jeunes n'est ce pas ?)";
		}
		return appa;
	}
	public  int calculPoids(int pTerre) {
		return 0;
	}

}
