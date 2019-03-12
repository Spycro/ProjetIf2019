package com.insa.projetif2019;
import java.util.Scanner;
public class Comete  {
	private int anneeAp;
	private int moisAp;
	private int jourAp;
	private double periode;
	private String famille;
	//private int[][] grilleDeNiveau;
	private String nom;
	
	public Comete (String n, int a, int m, int j, double p, String f){
		anneeAp=a;
		nom=n;
		moisAp=m;
		jourAp= j;
		periode=p;
		famille=f;
		//grilleDeNiveau = generergrille();
	}
	/*public Bloc [][] generergrille(){
		Bloc [][ grille=new Bloc[][];
		return grille;
	}*/
	
	public String info1 (){
		String info1= "Bonjour jeune explorateur, bienvenue sur"+nom+", je suis une comete. C'est � dire que je suis compos�e de trois parties : un noyau, une chevelure (je suis certaine qu'elle est plus belle que la tienne) et les queues."
				+ " Mon noyau et ma chevelure constituent ce que les scientifiques appellent ma t�te";
		return info1;	
	}
	public String info2() {
		String info2="Tes ancetres m'ont decouvert le "+jourAp+"/"+moisAp+"/"+anneeAp+". "
				+ "Ma periode orbitale est de\"+periode+\"ans  ";
		info2=info2+calculProchaineAp();
		return info2;
	}
	
	public String info3(){
		String info3=". Je suis bien plus vieille que toi tu sais, "
				+ "penses-tu que tu pourras me voir un jour ? Verifions/n";
	    return info3;
	}
	
	public String info4() {
		String info4="Je ne suis pas si diff�rente de toi tu sais, moi aussi j'ai une famille."
				+ "Je fais partie de la famille"+famille+"."; 
		return info4;
	}
	
	public String calculProchaineAp () {
		Scanner sc= new Scanner (System.in);
		String appa="";
		System.out.println (" Pour commencer, ton interface preferee a besoin de toi. En quelle ann�e somme nous ?");
		int date=sc.nextInt ();
		System.out.println ("Quel age as-tu? (je sais je sais mais faut assumer");
		int age=sc.nextInt();
		int anneepro=0;
		while (anneepro<date) {
			anneepro=(int) (anneepro+periode);
		}
		if ((age+(anneepro-date))<100){
			appa= "Tu auras le grand honneur de m'observer le" +jourAp+"/"+moisAp+"/"+anneepro+"Pour avoir cette chance /n;"
				+ "tu dois rester en bonne sant�: mange 5 fruits et l�gumes par jour.";
		}else {
			appa= "Sauf si la science a fait de grand progr�s depuis ma cr�ation, tu n'auras pas le grand honneur de m'observer le" +jourAp+"/"+moisAp+"/"+anneeAp+"Je suis vraiment desolee mais j'aime me faire attendre,"
					+ "tes enfants auront peut �tre plus de chance (ahhhhh ces jeunes n'est ce pas ?)";
		}
		return appa;
	}
	

}
