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
		String info1= "Bonjour jeune explorateur, bienvenue sur"+nom+", je suis une comete. C est a dire que je suis composee de trois parties : un noyau, une chevelure"
				+ " (je suis certaine qu'elle est plus belle que la tienne) et les queues."
				+ " Mon noyau et ma chevelure constituent ce que les scientifiques appellent ma tete";
		return info1;	
	}
	public String info2() {

		String info2=decouvreur+"m'a decouvert le "+jourAp+"/"+moisAp+"/"+anneeAp+". Original n'est ce pas";
		return info2;
	}
	public String info3() {
		String info3="Ma periode orbitale est de"+periode+"ans,"
				+ "cela correspond � la dur�e que je met pour revenir au m�me point. Bon j'avoue, je suis lente mais le principal"
				+ "c'est d'arriv�e"; 
		return info3;
	}
	public String info4(){
		String info4=". Je suis bien plus vieille que toi tu sais, "
				+ "penses-tu que tu pourras me voir un jour ? Verifions, Pour commencer, ton interface preferee a besoin de toi."
				+ "Indique dans le premier cadre, en quelle annee somme nous ? Maintenant LA question qui fache, renseigne dans la seconde case ton age et clic sur le bouton ";
		return info4;
	}
	

	
	public String calculProchaineAp (int date, int age) {
		String appa;
		int anneepro=0;
		while (anneepro<date) {
			anneepro=(int) (anneepro+periode);
		}
		if ((age+(anneepro-date))<100){
			appa= "Tu auras le grand honneur de m'observer le " +jourAp+"/"+moisAp+"/"+anneepro+" Pour avoir cette chance ;"
				+ "tu dois rester en bonne sante: mange 5 fruits et legumes par jour.";
		}else {
			appa= "Sauf si la science a fait de grand progres depuis ma creation, tu n'auras pas le grand honneur de m'observer le " +jourAp+"/"+moisAp+"/"+anneeAp+" Je suis vraiment desolee mais j'aime me faire attendre,"
					+ "tes enfants auront peut �tre plus de chance (ahhhhh ces jeunes n'est ce pas ?)";
		}
		return appa;
	}
	

}
