package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		
		/*for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (j > 7) {
					grille[i][j] = new Sol(i * 64, j * 64, Color.blue);
				}
				if(j==6 && i == 5) {
					grille[i][j] = new Sol(i * 64, j * 64, Color.blue);

				}

			}
		}*/
		
		
		joueur = new Personnage(50, 250, grille);
	}
	
	public void genererCarte() {
		
		//lecture du fichier de niveau
		
		FileInputStream flux = null;
		
		try {
			flux = new FileInputStream(map);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		char[] elements = new char[900];
		
		try {
			int n = 0;
			
			for(int i = 0; i < elements.length; i++) {
				if((n=flux.read()) >=0 ) {
					elements[i] = (char)n;
					System.out.print(elements[i]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(flux != null)
					flux.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Génération de la carte
		int k = 0;
		 for(int i = 0; i < grille.length; i++) {
			 for(int j = 0; j < grille[i].length; j++) {
				 if(elements[k] != '0')
					 this.grille[i][j]= new Bloc(this, i*64, j*64, elements[k]);
				 else
					 this.grille[i][j]= null;
				 k++;
			 }
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
					g.drawImage(grille[i][j].sprite, j*64, i*64, pCarte);
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
		// marche pas :(
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j] != null && joueur.hitBox.intersects(grille[i][j].hitBox))
					return true;
			}
		}

		return false;
	}

	public void checkMvt(Set<Integer> moveSet) {

	}

}
