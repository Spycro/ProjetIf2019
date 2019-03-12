package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PanneauDeJeu extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Bloc[][] grille;
	private Personnage joueur;
	private File map;
	private JPanel pCarte;
	

	public PanneauDeJeu() {

		grille = new Bloc[10][90];
		//Modifications par Lolo
		map = new File("bin/level.txt");
		pCarte = new JPanel();
		pCarte.setBounds(0, 0, 5760, 640);
		pCarte.setOpaque(false);
		
		this.genererCarte();
		this.add(pCarte);
		
		joueur = new Personnage(50, 250, grille);
	}
	
	public void genererCarte() {
		int f=0;
		
		String [] strStore = new String[10];
		BufferedReader br = null;
	    try {
	        String sCurrentLine;
	        br = new BufferedReader(new FileReader("bin/level.txt"));  //file name with path
	        while ((sCurrentLine = br.readLine()) != null) {

	            String[] strArr = sCurrentLine.split("\n");
	            for(String str:strArr){
	                    strStore[f++] = str;
	            }
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
			
	    for(String str:strStore) {
	    	//System.out.println(str);
	    }
	    
	    int k = 0;
	    for(int i = 0; i<strStore.length;i++) {
	    	for (int j = 0; j<grille[i].length;j++) {
	    		grille[i][j] = new Bloc(this, j*64,i*64, strStore[i].charAt(k));
	    		System.out.print(strStore[i].charAt(k));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				if (grille[i][j] != null) {
					g.drawImage(grille[i][j].sprite, grille[i][j].coordX, grille[i][j].coordY, pCarte);
					g.drawRect(grille[i][j].coordX, grille[i][j].coordY, Bloc.COTES, Bloc.COTES);
				}
				else {
					g.setColor(Color.red);
					g.drawRect(j*64,i*64,Bloc.COTES,Bloc.COTES);
				}
			}
		}

		joueur.dessineJoueur(g, this);

	}

	public Personnage getJoueur() {
		return joueur;
	}

	public Bloc[][] getGrille() {
		return grille;
	}

	/**
	 * Check si le personnage est en collison avec un bloc
	 * 
	 * @return true si coliision false si non
	 */
	public boolean collision() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j].typeBloc != "0" && joueur.hitBox.intersects(grille[i][j].hitBox))
					return true;
			}
		}

		return false;
	}

}
