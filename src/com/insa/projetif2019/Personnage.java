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
	
	private final int dX = 2;
	private final int dY = 2;
	
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
		hitBox = new Rectangle(x,y,LARGEUR, HAUTEUR -20);
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
		hitBox = new Rectangle(x,y, LARGEUR, HAUTEUR);
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public void setX(int pX) {x = pX;}
	
	public void setY(int pY) {y = pY;}
	
	/**
	 * Gere le deplacement du joueur 
	 * @param moveSet Set d'int contenant les touches actuellements pressees
	 */
	
	public void move(Set<Integer> moveSet) {
		
		
		if(moveSet.contains(39)) {
			x += dX;
		}
		
		if(moveSet.contains(37)) {
			x-=dX;
		}
		
		if(moveSet.contains(40)) {
			y+=dY;
		}
		
		if(moveSet.contains(38)) {
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
		
		//////////////////////////////// HITBOX (PRODUCTION)
		g.drawRect(x, y, hitBox.width, hitBox.height);
		///////////////////////////////
	}
	

	
	/**
	 * Met A jour l'emplacement de la hitbox
	 */
	public void refreshHB() {
		hitBox.setLocation(x,y);
	}
	
	public void preMouvement(Set<Integer> moveSet, Bloc[][] grille) {
		//futur emplacement
		//collision contre cet emplacement
		
		Rectangle placeHolder = hitBox.getBounds();
		
		if(moveSet.contains(39)) {
			placeHolder.x += dX;
		}
		
		if(moveSet.contains(37)) {
			placeHolder.x-=dX;
		}
		
		if(moveSet.contains(40)) {
			placeHolder.y+=dY;
		}
		
		if(moveSet.contains(38)) {
			placeHolder.y-=dY;
		}
		if(!collision(grille, placeHolder)) {
			move(moveSet);
		}
		else {
			System.out.println("Collision !");
		}
		}
	
	public boolean collision(Bloc[][] grille, Rectangle pH) {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j] != null && pH.intersects(grille[i][j].hitBox))
					return true;
			}
		}
		
		
		return false;
	}
	
	public void falling(Bloc[][] grille) {
		int gravite = 20;
		if(!collision((grille), new Rectangle(x,y+gravite, hitBox.width,hitBox.height))) {
			y+=gravite;
		}
		else {
			y = plusProcheSol(grille);
		}
	}
	
	public int plusProcheSol(Bloc [][] grille) {
		Rectangle temp = new Rectangle(hitBox);
		while(collision(grille, temp)){
			temp.y-=1;
		}
		
		return temp.y+1;
	}
	
	public void jump() {
		
	}
	
	
}
