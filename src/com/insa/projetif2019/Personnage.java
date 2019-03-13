package com.insa.projetif2019;

import java.awt.Image;
import java.awt.Color;
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
 * @author Lucas, Janna, Loic
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
	private final double FRICTION = 0.92;

	private boolean onGround;

	private Bloc[][] monde;
	private Bloc solCourant;

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
		g.setColor(Color.white);
		g.drawRect(posX, posY, hitBox.width, hitBox.height);
		g.drawString("position X : "+posX+" Position Y : "+posY, 0, 30);
		g.drawString("vitesse X : "+speedX+" vitesse Y : "+speedY, 0, 60);
		g.drawString("on Ground :"+onGround, 0, 90);
		g.drawString("collision : "+collision(), 200, 30);

		///////////////////////////////////////////////////////
		
		if(solCourant != null) {
			g.setColor(Color.white);
			g.fillRect(solCourant.getX(), solCourant.getY(), Bloc.getCote(), Bloc.getCote());
		}
		///////////////////////////////
	}

	/**
	 * Met A jour l'emplacement de la hitbox
	 */
	public void refreshHB() {
		hitBox.setLocation(posX, posY);
	}

	public void preMouvement(Set<Integer> moveSet) {
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
		// 39 droites
		// 37 gauche
		// 38 haut
		// 40 bas

	}

	public boolean collision() {
		for (int i = 0; i < monde.length; i++) {
			for (int j = 0; j < monde[0].length; j++) {
				if (monde[i][j].getType() != '0' && hitBox.intersects(monde[i][j].hitBox)) {
					solCourant = monde[i][j];
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean collision2() {
		Rectangle pH = new Rectangle(hitBox);
		pH.y += 20;
		for (int i = 0; i < monde.length; i++) {
			for (int j = 0; j < monde[0].length; j++) {
				if (monde[i][j].getType() != '0' && hitBox.intersects(monde[i][j].hitBox)) {
					solCourant = monde[i][j];
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
		if(onGround) {
			acceleration(0, -15);
			onGround = false;
		}

	}

	public void maj() {
		if(!onGround) 
			acceleration(0, gravity);

		onGround = checkGround();
		if (collision()) {
			move(-speedX,0);
			if(speedX < 0) {
				speedX = 0;
			}
			if(speedX > 0) {
				speedX = 0;
			}
			if(speedY>0) {
				setY(solCourant.getX() - Bloc.getCote() - (HAUTEUR - Bloc.getCote()));
				System.out.println("vitesse y = 0");
				speedY = 0;
				
			}
			if(speedY<0) {
				speedY = 0;
			}
		}
		
		else {
			
		}
		
		
		move(speedX, speedY);
		speedX *= FRICTION;
		speedY *= FRICTION;
		refreshHB();
	}
	
	private boolean checkGround() {
		Rectangle pH = new Rectangle(hitBox);
		pH.y += 1;
		if(solCourant != null) {
			//System.out.println(pH.intersection(solCourant.hitBox));
			return pH.intersection(solCourant.hitBox).height >0 && pH.intersection(solCourant.hitBox).width >0 && pH.intersection(solCourant.hitBox).width<64;
		}
		return false;
	}
}
