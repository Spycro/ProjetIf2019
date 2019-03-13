package com.insa.projetif2019;
import java.util.Scanner;
public class Comete  {
	private int anneeap;
	private int moisap;
	private int jourap;
	private double periode;
	//private int[][] grilleDeNiveau;
	private String nom;
	private String decouvreur;
	public Comete (String n, int a, int m, int j, double p, String d){
		anneeap=a;
		nom=n;
		decouvreur=d;
		moisap=m;
		jourap= j;
		periode=p;
		//grilleDeNiveau = generergrille();
	}
	/*public Bloc [][] generergrille(){
		Bloc [][ grille=new Bloc[][];
		return grille;
	}*/
	
	public String info1 (){
		String info1= "Bonjour jeune explorateur, bienvenue sur"+nom+", je suis une comete. C'est à dire que je suis composée de trois parties : un noyau, une chevelure (je suis certaine qu'elle est plus belle que la tienne) et les queues."
				+ " Mon noyau et ma chevelure constituent ce que les scientifiques appellent ma tête";
		return info1;	
	}
	public String info2() {
		String info2=decouvreur+"m'a decouvert le "+jourap+"/"+moisap+"/"+anneeap+". Original n'est ce pas";
		return info2;
	}
	public String info3() {
		String info3="Ma periode orbitale est de"+periode+"ans,"
				+ "cela correspond à la durée que je met pour revenir au même point. Bon j'avoue, je suis lente mais le principal"
				+ "c'est d'arrivée"; 
		return info3;
	}
	public String info4(){
		String info4=". Je suis bien plus vieille que toi tu sais, "
				+ "penses-tu que tu pourras me voir un jour ? Verifions/n";
		info4=info4+calculprochaineap();
	    return info4;
	}
	
	
	
	public String calculprochaineap () {
		Scanner sc= new Scanner (System.in);
		String appa="";
		System.out.println (" Pour commencer, ton interface preferee a besoin de toi. En quelle année somme nous ?");
		double date=sc.nextDouble ();
		System.out.println ("Quel age as-tu? (je sais je sais mais faut assumer");
		int age=sc.nextInt();
		double anneepro=0.0;
		while (anneepro<date) {
			anneepro=anneepro+periode;
		}
		if ((age+(anneepro-date))<100){
			appa= "Tu auras le grand honneur de m'observer le" +jourap+"/"+moisap+"/"+anneepro+"Pour avoir cette chance /n;"
				+ "tu dois rester en bonne santé: mange 5 fruits et légumes par jour.";
		}else {
			appa= "Sauf si la science a fait de grand progrès depuis ma création, tu n'auras pas le grand honneur de m'observer le" +jourap+"/"+moisap+"/"+anneeap+"Je suis vraiment desolee mais j'aime me faire attendre,"
					+ "tes enfants auront peut être plus de chance (ahhhhh ces jeunes n'est ce pas ?)";
		}
		return appa;
	}
	

}
