package com.insa.projetif2019;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		FenetrePrincipale fen=null;
		// Declaration de tous les astres ;) 
				Planete Terre= new Planete (6371.0,365.25,-88,58,5.97*Math.pow(10,24),"Terre","Tellurique");
				Planete Mercure= new Planete (2439.7,87.96,-173,427,3.3*Math.pow(10,23),"Mercure","Tellurique");
				Planete Venus= new Planete(6051.8,224.7,446,490,4.87*Math.pow(10,24),"Venus","Tellurique");
				Planete Mars= new Planete (3389.5,686.9,-87,-5,6.41*Math.pow(10,23),"Mars","Tellurique");
				Planete Jupiter=new Planete(69911.0,4335.35,-161,-108,1.898*Math.pow(10,27),"Jupiter","gazeuse");
				Planete Saturne= new Planete (58232.0,10757.0,-189,-139,5.68*Math.pow(10,26),"Saturne","gazeuse");
				Planete Uranus=new Planete(25362.0,30687.15,-220,-197,8.68*Math.pow(10,25),"Uranus","de glace");
				Planete Neptune=new Planete(24622.0,60224.9,-218,-201,1.0247*Math.pow(10,26),"Neptune","de glace");
				Comete Halley=new Comete("Halley",1994,2,16,76.09,"Edmond Halley");
				Comete Tempel=new Comete ("Tempel",1867,4,3,5.56, "Ernst Wilhelm Tempel");
				Comete Tuttle= new Comete ("Mechain-Tuttle",1790,1,9,13.58 ,"Pierre Mechain");
				ArrayList<Astre> listeAstre = new ArrayList <Astre> ();
				listeAstre.add(Terre);
				listeAstre.add(Mercure);
				listeAstre.add(Venus);
				listeAstre.add(Mars);
				listeAstre.add(Jupiter);
				listeAstre.add(Saturne);
				listeAstre.add(Uranus);
				listeAstre.add(Neptune);
				listeAstre.add(Halley);
				listeAstre.add(Tempel);
				listeAstre.add(Tuttle);
		//Fichier de sauvegarde
		
		fen = new FenetrePrincipale(listeAstre);
	   

	}

}
