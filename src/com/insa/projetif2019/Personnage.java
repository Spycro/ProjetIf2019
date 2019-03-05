package com.insa.projetif2019;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/**
 * Personnage joueur
 * @author Lucas
 *
 */


public class Personnage {
	
	//ATTRIBUTS
	////////////////////////////////////////////////////////////////////////////////////////
	Image sprite;
	Rectangle hitBox;
	private int x;
	private int y;
	
	private final int dX = 1;
	private final int dY = 1;
	
	private final int LARGEUR = 40;
	private final int HAUTEUR = 75;
	/////////////////////////////////////////////////////////////////////////////////////////
	
	///METHODES
	public Personnage() {
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = 0;
		y = 0;
		hitBox = new Rectangle(x,y,LARGEUR, HAUTEUR);
	}
	
	public Personnage(int pX, int pY) {
		//creation du personnage
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		x= pX;
		y = pY;
		hitBox = new Rectangle(pX,pY, LARGEUR, HAUTEUR);
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public void setX(int pX) {x = pX;}
	
	public void setY(int pY) {y = pY;}
	
	/**
	 * Gere le deplacement du joueur 
	 * @param touche Set d'int contenant les touches actuellements pressees
	 */
	
	public void move(Set<Integer> touche) {
		
		
		if(touche.contains(39)) {
			x += dX;
		}
		
		if(touche.contains(37)) {
			x-=dX;
		}
		
		if(touche.contains(40)) {
			y+=dY;
		}
		
		if(touche.contains(38)) {
			y-=dY;
		}
		 // 39 droite
		 // 37 gauche 
		 // 38 bas
		 // 40 haut
		refreshHB();
	}
	
	/**
	 * permet le dessin du sprite du joureur 
	 * @param g objet graphique
	 * @param obs endroit ou sera afficher l'image
	 */
	
	public void dessineJoueur(Graphics g, ImageObserver obs) {
		g.drawImage(sprite, x, y, LARGEUR, HAUTEUR, obs);
	}
	
	private boolean canMove() {
		return true;
	}
	
	/**
	 * Met A jour l'emplacement de la hitbox
	 */
	public void refreshHB() {
		hitBox.setLocation(x,y);
	}
}
