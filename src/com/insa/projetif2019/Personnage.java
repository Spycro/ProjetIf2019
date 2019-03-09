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
 * 
 * @author Lucas
 *
 */

public class Personnage {

	// ATTRIBUTS
	////////////////////////////////////////////////////////////////////////////////////////
	Image sprite;
	Rectangle hitBox;
	private int posX;
	private int posY;
	private double speedX;
	private double speedY;

	private double gravity;

	private boolean onGround;

	private Bloc[][] monde;

	private final int LARGEUR = 40;
	private final int HAUTEUR = 75;
	/////////////////////////////////////////////////////////////////////////////////////////

	/// METHODES
	public Personnage() {
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		posX = 0;
		posY = 0;
		speedX = 0;
		speedY = 0;
		gravity = 5.0;
		hitBox = new Rectangle(posX, posY, LARGEUR, HAUTEUR - 20);
	}

	public Personnage(int pX, int pY, Bloc[][] pMonde) {
		// creation du personnage
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		posX = pX;
		posY = pY;
		speedX = 0;
		speedY = 0;
		gravity = 1.0;
		monde = pMonde;
		hitBox = new Rectangle(posX, posY, LARGEUR, HAUTEUR);
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public void setX(int pX) {
		posX = pX;
	}

	public void setY(int pY) {
		posY = pY;
	}

	/**
	 * Gere le deplacement du joueur
	 * 
	 * @param moveSet Set d'int contenant les touches actuellements pressees
	 */

	public void move(double dX, double dY) {

		posX += dX;
		posY += dY;
		refreshHB();

	}

	/**
	 * permet le dessin du sprite du joureur
	 * 
	 * @param g   objet graphique
	 * @param obs endroit ou sera afficher l'image
	 */

	public void dessineJoueur(Graphics g, ImageObserver obs) {
		g.drawImage(sprite, posX, posY, LARGEUR, HAUTEUR, obs);

		//////////////////////////////// HITBOX (PRODUCTION)
		g.drawRect(posX, posY, hitBox.width, hitBox.height);
		///////////////////////////////
	}

	/**
	 * Met A jour l'emplacement de la hitbox
	 */
	public void refreshHB() {
		hitBox.setLocation(posX, posY);
	}

	public void preMouvement(Set<Integer> moveSet, Bloc[][] grille) {
		// futur emplacement
		// collision contre cet emplacement

		if (moveSet.contains(39)) {

			deplacementDroite();
		}

		if (moveSet.contains(37)) {

			deplacementGauche();
		}

		if (moveSet.contains(40)) {

		}

		if (moveSet.contains(38)) {

			jump();
		}

		else {
			System.out.println("Collision !");
		}
		// 39 droites
		// 37 gauche
		// 38 haut
		// 40 bas

		System.out.println(speedX + " " + speedY);
		System.out.println(gravity);

	}

	public boolean collision() {
		for (int i = 0; i < monde.length; i++) {
			for (int j = 0; j < monde[0].length; j++) {
				if (monde[i][j] != null && hitBox.intersects(monde[i][j].hitBox)) {
					return true;
				}
			}
		}
		return false;
	}

	public void acceleration(double aX, double aY) {
		speedX += aX;
		speedY += aY;

	}

	public void deplacementDroite() {
		if (speedX < 2) {
			acceleration(1, 0);
		}
	}

	public void deplacementGauche() {
		if (speedX > -2) {
			acceleration(-1, 0);
		}
	}

	public void jump() {

		acceleration(0, -5);

	}

	public void maj() {
		move(speedX, speedY);
		acceleration(0, gravity);
		if (collision()) {
			speedX = 0;
			speedY = 0;
		}
	}
}
