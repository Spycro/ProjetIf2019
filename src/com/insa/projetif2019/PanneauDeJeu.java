package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Set;

import javax.imageio.ImageIO;


public class PanneauDeJeu extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Bloc[][] grille;
	private Personnage joueur;
	private File map;
	private JPanel pCarte;
	protected Astre niveau;
	protected PanneauPrincipal paternel;
	
	
	public PanneauDeJeu(Astre nomNiveau,PanneauPrincipal p) {
		paternel=p;
		grille = new Bloc[10][90];
		niveau = nomNiveau;
		map = new File("bin/"+niveau.getNom()+".txt");
		pCarte = new JPanel();
		pCarte.setBounds(0, 0, 5760, 640);
		pCarte.setOpaque(false); // Fond transparent
		
	
		this.genererCarte();
		this.add(pCarte);
		
		joueur = new Personnage(50, 250, grille, this, paternel);
	}
	
	public void genererCarte() {
		int f=0;
		
		String [] strStore = new String[10];
		BufferedReader br = null;
	    try {
	        String sCurrentLine;
	        br = new BufferedReader(new FileReader(map.getCanonicalPath()));  //file name with path
	        while ((sCurrentLine = br.readLine()) != null) {

	            String strArr = sCurrentLine;
	            
	            strStore[f++] = strArr;
	            
	        }
	    } 
	    catch (IOException e) {
	            e.printStackTrace();
	        } 
	    finally {
	            try {
	                if (br != null)br.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
			
	    
	    
	    int k = 0;
	    for(int i = 0; i<strStore.length;i++) {
	    	for (int j = 0; j<grille[i].length;j++) {
	    		grille[i][j] = new Bloc( pCarte, j*64,i*64, strStore[i].charAt(k), niveau.getNom());
	    		k++;
	    	}
	    	k=0;
	    	System.out.println();
	    }
	    
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		// Dessin du background (ca marche !!!)
		// Dessine avec l'image "earthBG"
		try {

			Image bg = ImageIO.read(new File("bin/earthBG.jpg"));
			g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}

		currentOffset(g);
		
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j].getType() != '0') {
					grille[i][j].dessineBloc(g, this);
					//g.drawImage(grille[i][j].sprite, grille[i][j].getX(), grille[i][j].getY(), this);
					/*g.setColor(Color.black);
					g.drawRect(grille[i][j].getX(), grille[i][j].getY(), Bloc.getCote(), Bloc.getCote());*/
				}
				else {
					/*g.setColor(Color.red);
					g.drawRect(grille[i][j].getX(), grille[i][j].getY(),Bloc.getCote(),Bloc.getCote());*/
				}
			}
		}

		joueur.dessineJoueur(g, pCarte);
		

	}

	public Personnage getJoueur() {
		return joueur;
	}

	public Bloc[][] getGrille() {
		return grille;
	}
	public Astre getAstre() {
		return niveau;
	}
	
	private void currentOffset(Graphics g) {
		if(joueur.getX() > 350) {
			g.translate(-(joueur.getX() - 350), 0);
		}
	}
	public void miseAJourGrille (int i, int j) {
		grille[i][j].setType('0');
		repaint();
	}

}
