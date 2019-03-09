package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
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

	public PanneauDeJeu() {

		grille = new Bloc[10][10];

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (j > 7) {
					grille[i][j] = new Sol(i * 64, j * 64, Color.blue);
				}
				

			}
		}
		joueur = new Personnage(50, 250, grille);
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
					grille[i][j].dessine(g);
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
