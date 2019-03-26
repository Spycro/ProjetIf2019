package com.insa.projetif2019;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class test {

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
				Comete Tuttle= new Comete (" M�chain-Tuttle",1790,1,9,13.58 ,"Pierre M�chain");
				ArrayList<Astre> ListeAstre = new ArrayList <Astre> ();
				ListeAstre.add(Terre);
				ListeAstre.add(Mercure);
				ListeAstre.add(Venus);
				ListeAstre.add(Mars);
				ListeAstre.add(Jupiter);
				ListeAstre.add(Saturne);
				ListeAstre.add(Uranus);
				ListeAstre.add(Neptune);
				ListeAstre.add(Halley);
				ListeAstre.add(Tempel);
				ListeAstre.add(Tuttle);
		//Fichier de sauvegarde
		
		File savefile;
		
		savefile = new File("file0.sav");
		
		if(!savefile.isFile()) { 
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter("file0.sav"));
				bw.write("Terre");
	        	bw.close();
	        	savefile = new File("file0.sav");
			} catch (IOException e) {
				System.out.println("Erreur lors de l'ecriture du fichier de sauvegarde");
				e.printStackTrace();
			}
        	
		}
		
		String niveauActuel = "";
		BufferedReader br = null;
	    try {
	        br = new BufferedReader(new FileReader(savefile.getCanonicalPath()));
	        niveauActuel = br.readLine();
	    } 
	    catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Erreur lors du chargement du fichier de sauvegarde !");
	        } 
	    finally {
	            try {
	                if (br != null)br.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    System.out.println(niveauActuel);	
	   for (int i=0; i<ListeAstre.size(); i++) {
	    	if (ListeAstre.get(i).getNom().contains(niveauActuel)) {
	    		fen = new FenetrePrincipale(ListeAstre.get(i));		
	    	}		
	    }

	}

}
