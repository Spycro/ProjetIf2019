package com.insa.projetif2019;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Personnage joueur
 * 
 * @author Lucas, Janna, Loic
 *
 */

public class Personnage implements ActionListener {

	// ATTRIBUTS
	////////////////////////////////////////////////////////////////////////////////////////
	Image sprite;
	Image coeur;
	private Rectangle hitBox;
	private int posX;
	private int posY;
	private double speedX;
	private double speedY;
	private final double speedMax = 5;

	private double gravity;
	private final double FRICTION = 0.92;
	private PanneauDeJeu parent;
	private boolean onGround;
	private int[] posMonde;

	private Bloc[][] monde;
	private Bloc blocRencontre;

	private final int LARGEUR = 40;
	private final int HAUTEUR = 75;

	boolean enVie = true;
	public int pointDeVie;

	private Timer invincibleTimer;
	private boolean invincible;

	/////////////////////////////////////////////////////////////////////////////////////////

	/// METHODES
	public Personnage() {
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		posX = 0;
		posY = 0;
		speedX = 0;
		speedY = 0;
		gravity = 5.0;
		hitBox = new Rectangle(posX, posY, LARGEUR, HAUTEUR - 20);
		parent = null;
		invincibleTimer = new Timer(33, this);
	}

	/**
	 * Constructeur de Personnage
	 * Il iniatilise les differenents sprite ainsi que sa position initiale
	 * Il y en a un seul dans le jeu
	 * @param pX Position X de depart
	 * @param pY Position Y de Depart
	 * @param pMonde Carte du Monde
	 * @param papa PanneauDeJeu invoqueur
	 */
	
	public Personnage(int pX, int pY, Bloc[][] pMonde, PanneauDeJeu papa) {
		// creation du personnage
		parent = papa;
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
			coeur = ImageIO.read(new File("bin/heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pointDeVie = 3;
		posX = pX;
		posY = pY;
		speedX = 0;
		speedY = 0;
		gravity = 1.0;
		monde = pMonde;
		hitBox = new Rectangle(posX, posY, LARGEUR, HAUTEUR);
		posMonde = positionCourante();
		invincibleTimer = new Timer(2000, this);
		invincible = false;
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

		if (blocRencontre != null) {
			g.setColor(Color.white);
			g.fillRect(blocRencontre.getX(), blocRencontre.getY(), Bloc.getCote(), Bloc.getCote());
		}
		if (posX > 350 && posX < 5150)
			g.translate(posX - 350, 0);
		else if(posX >= 5150){
			g.translate(5150 - 350, 0);
		}
		for (int i = 0; i < pointDeVie; i++) {

			g.drawImage(coeur, i * 40, 0, 32, 32, obs);
		}
		g.drawString("position X : " + posX + " Position Y : " + posY, 0, 30);
		g.drawString("vitesse X : " + speedX + " vitesse Y : " + speedY, 0, 60);
		g.drawString("on Ground :" + onGround, 0, 90);
		g.drawString("collision : " + collision(), 200, 30);
		g.drawString("vie :" + pointDeVie, 400, 30);
		///////////////////////////////
	}

	/**
	 * Met A jour l'emplacement de la hitbox
	 */
	public void refreshHB() {
		hitBox.setLocation(posX, posY);
	}

	/**
	 * Effectue des actions en fonction des touches pressees
	 * @param moveSet Set contenant les touches actuellement pressees
	 */
	
	public void preMouvement(Set<Integer> moveSet) {

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

	
		// 39 droite
		// 37 gauche
		// 38 haut
		// 40 bas
		

	}

	public boolean collision() {

		for (int i = 0; i < monde.length; i++) {
			for (int j = 0; j < monde[0].length; j++) {
				if (monde[i][j].getType() != '0' && hitBox.intersects(monde[i][j].getHitBox())) {

					blocRencontre = monde[i][j];

					if (blocRencontre.getType() == '0' && i == monde.length - 1) {
						parent.endLevel(false);
					}

					else if (blocRencontre.getType() == '7') {
						pointVie(1);
						parent.miseAJourGrille(i, j);
					} else if (blocRencontre.getType() == '8') {

						parent.pause();
						FenetreInfo fenetre1 = new FenetreInfo(parent.getAstre(), 1);
						parent.miseAJourGrille(i, j);
						speedX = 0;
						speedY = 0;

					} else if (blocRencontre.getType() == '9') {
						parent.pause();
						FenetreInfo fenetre2 = new FenetreInfo(parent.getAstre(), 2);
						parent.miseAJourGrille(i, j);

					} else if (blocRencontre.getType() == 'A') {
						parent.pause();
						FenetreInfo fenetre3 = new FenetreInfo(parent.getAstre(), 3);
						parent.miseAJourGrille(i, j);

					} else if (blocRencontre.getType() == 'B') {
						parent.pause();
						FenetreInfo fenetre4 = new FenetreInfo(parent.getAstre(), 4);
						parent.miseAJourGrille(i, j);

					}

					else if (blocRencontre.getType() == 'C') {
						parent.endLevel(true);
					}

					else if (blocRencontre.getType() == 'E') {
						if (!getInvincible()) {
							pointVie(-1);
							setInvincible();
						}
					}

					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Modifie l'acceleration actuelle
	 * @param aX acc en X
	 * @param aY acc en Y
	 */
	
	public void acceleration(double aX, double aY) {
		speedX += Math.round(aX);
		speedY += Math.round(aY);

	}

	/**
	 * Une acceleration a droite dans la limite
	 * de speedMax
	 */
	
	public void deplacementDroite() {
		if (speedX < speedMax) {
			acceleration(3, 0);
		}
	}

	/**
	 * Une acceleration a gauche dans la limite
	 * de speedMax
	 */
	
	public void deplacementGauche() {
		if (speedX > -speedMax) {
			acceleration(-3, 0);
		}
	}

	/**
	 * Permet le saut du personnage
	 * acceleration negative
	 */
	
	public void jump() {
		if (onGround) {
			acceleration(0, -15);
			onGround = false;
		}

	}

	/**
	 * Methode principale du personnage
	 * Gere le prochain mouvement du personnage
	 * invoquée par PanneauPrincipal	
	 */
	
	public void maj() {
		enVie();
		onGround = checkGround();

		// On verifie si le personnage est dans les limites de la carte

		if (getY() > monde.length * 64 - HAUTEUR) {
			pointVie(-pointDeVie);
		}

		// Verification du sol

		if (onGround) {
			if (speedY > 0) {

				setY(blocRencontre.getY() - Bloc.getCote() - (HAUTEUR - Bloc.getCote()));
				speedY = 0;

			}
		} else {
			acceleration(0, gravity);
		}

		if (getX() < 0) {
			setX(0);
			speedX = 0;
		}

		else if (getX() >= (monde[0].length - 1) * 64) {
			if (getX() > (monde[0].length) * 64 - HAUTEUR && speedX > 0) {
				setX(monde[0].length * 64 - LARGEUR);
				speedX = 0;
			}
		}

		else if (getX() > 64) {
			if (checkFront()) {
				if (speedX > 0) {
					setX(blocRencontre.getX() - LARGEUR);
					speedX = 0;
				}
			}

			// Verification du bloc arriere

			if (checkBack()) {
				if (speedX < 0) {
					setX(blocRencontre.getX() + Bloc.getCote());
					speedX = 0;
				}
			}

			if (checkTop()) {
				if (speedY < 0) {
					setY(blocRencontre.getY() + Bloc.getCote());
					speedY = 0;
					acceleration(0, gravity);
				}
			}
		}

		move(speedX, speedY);
		speedX *= FRICTION;
		speedY *= FRICTION;

		refreshHB();
		posMonde = positionCourante();
	}

	/**
	 * Verifie le sol
	 * @return true si un sol present
	 */
	
	private boolean checkGround() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];

		Bloc blocBas = monde[indiceY][indiceX];

		if (blocBas != null) {
			if (blocBas.estSol()) {
				return true;
			}
		}
		
		if(indiceY+1 >= monde.length && !blocBas.estSol()) {
			enVie = false;
			return false;
		}

		blocBas = monde[indiceY + 1][indiceX];
		
		if(Math.round((getX()+LARGEUR)/64) > indiceX && indiceX+1 < monde[0].length ) {
			if (monde[indiceY + 1][indiceX + 1].estSol()) {
				indiceX += 1;
				blocBas = monde[indiceY + 1][indiceX];
			}
		}
		
		Rectangle pH = new Rectangle(hitBox);
		pH.y += 1;
		if (blocBas != null) {
			if (blocBas.estSol()) {
				if (pH.intersection(blocBas.getHitBox()).height > 0) {
					blocRencontre = blocBas;
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * verifie en face
	 * @return true si bloc en face
	 */
	
	private boolean checkFront() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];

		Bloc blocFront = monde[indiceY][indiceX];

		if (blocFront != null) {
			if (blocFront.estSol()) {
				return true;
			}
		}

		blocFront = monde[indiceY][indiceX + 1];
		Rectangle pH = new Rectangle(hitBox);
		pH.x += 1;
		if (blocFront != null) {
			if (blocFront.estSol()) {
				if (pH.intersection(blocFront.getHitBox()).width > 0) {
					blocRencontre = blocFront;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * verifie au dessus (pendant les sauts par exemple
	 * @return true si bloc au dessus
	 */
	
	private boolean checkTop() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];
		
		if(indiceY - 1 < 0 || indiceY-2 < 0) {
			return false;
		}

		Bloc blocTop = monde[indiceY - 1][indiceX];
		
		if(Math.round((getX()+LARGEUR)/64) > indiceX && indiceX+1 < monde[0].length ) {
			if (monde[indiceY - 1][indiceX + 1].estSol()) {
				indiceX += 1;
				blocTop = monde[indiceY - 1][indiceX];
			}
		}

		if (blocTop != null) {
			if (blocTop.estSol()) {
				return true;
			}
		}
		
		else {

			blocTop = monde[indiceY - 2][indiceX];
			Rectangle pH = new Rectangle(hitBox);
			pH.x += 1;
			if (blocTop != null) {
				if (blocTop.estSol()) {
					if (pH.intersection(blocTop.getHitBox()).height > 0) {
						blocRencontre = blocTop;
						return true;
					}
				}
			}

		}

		return false;
	}
	
	/**
	 * verife en bloc arriere
	 * @return true si bloc derriere
	 */
	
	private boolean checkBack() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];

		Bloc blocBack = monde[indiceY][indiceX];

		if (blocBack != null) {
			if (blocBack.estSol()) {
				return true;
			}
		}

		blocBack = monde[indiceY][indiceX - 1];
		Rectangle pH = new Rectangle(hitBox);
		pH.x -= 1;
		if (blocBack != null) {
			if (blocBack.estSol()) {
				if (pH.intersection(blocBack.getHitBox()).width > 0) {
					blocRencontre = blocBack;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Calcule la position actuelle des pieds
	 * du personnage
	 * @return x et y
	 */
	
	private int[] positionCourante() {
		// calcul x
		int x = posX / 64;
		// calcul y
		int y = posY / 64 + 1;
		int[] a = { x, y };
		return a;

	}
	
	/**
	 * Verifie si le personnage est troujours en vie
	 */
	
	private void enVie() {
		if (pointDeVie <= 0) {
			enVie = false;
		}
	}
	
	/**
	 * Ajoute ou retire un nombre de point de vie
	 * @param a int souvent 1 ou -1
	 */
	
	public void pointVie(int a) {

		if ((a > 0) && (pointDeVie < 3)) {
			pointDeVie = pointDeVie + a;
		}
		if ((a <= 0)) {
			pointDeVie = pointDeVie + a;
		}

	}
	 
	/**
	 * Rend le personnage invincible par un Timer
	 * de 2 secondes environ
	 */
	
	public void setInvincible() {
		if (!invincibleTimer.isRunning()) {
			invincibleTimer.start();
			invincible = true;
			try {
				sprite = ImageIO.read(new File("bin/astronautInv.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean getInvincible() {
		return invincible;
	}


	
	/**
	 * 
	 * @return Valeur de EnVie
	 */
	
	
	public boolean getEnVie() {
		return enVie;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public Rectangle getHitbox() {
		return hitBox;
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
	 * implementation
	 * Utile pour l'attribut invincible
	 */
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == invincibleTimer) {
			invincible = false;
			try {
				sprite = ImageIO.read(new File("bin/astronaut.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			invincibleTimer.stop();
		}

	}

}
