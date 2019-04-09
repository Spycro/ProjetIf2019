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
	Rectangle hitBox;
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
		parent= null;
		invincibleTimer = new Timer(33,this);
	}

	public Personnage(int pX, int pY, Bloc[][] pMonde, PanneauDeJeu papa) {
		// creation du personnage
		parent=papa;
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
		invincibleTimer = new Timer(2000,this);
		invincible = false;
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
	
	public boolean getInvincible() {
		return invincible;
	}
	
	public void setInvincible() {
		if(!invincibleTimer.isRunning()) {
			invincibleTimer.start();
			invincible = true;
		}
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
		if(getInvincible()) {
			g.setColor(Color.red);
		}
		else {
			g.setColor(Color.white);
		}
		g.drawRect(posX, posY, hitBox.width, hitBox.height);
		//g.fillRect(posMonde[0]*64, posMonde[1]*64, 64, 64);
		
		
		

		///////////////////////////////////////////////////////
		
		if(blocRencontre != null) {
			g.setColor(Color.white);
			g.fillRect(blocRencontre.getX(), blocRencontre.getY(), Bloc.getCote(), Bloc.getCote());
		}
		if(posX > 350)
			g.translate(posX-350, 0);
		for(int i=0;i<pointDeVie;i++) {
			
			g.drawImage(coeur, i*40, 0, 32, 32, obs);
		}
		g.drawString("position X : "+posX+" Position Y : "+posY, 0, 30);
		g.drawString("vitesse X : "+speedX+" vitesse Y : "+speedY, 0, 60);
		g.drawString("on Ground :"+onGround, 0, 90);
		g.drawString("collision : "+collision(), 200, 30);
		g.drawString("vie :"+ pointDeVie, 400, 30);
		///////////////////////////////
	}

	/**
	 * Met A jour l'emplacement de la hitbox
	 */
	public void refreshHB() {
		hitBox.setLocation(posX, posY);
	}

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
		
		if(moveSet.contains(27)) {
			
		}
		// 39 droite
		// 37 gauche
		// 38 haut
		// 40 bas
		// 27 echap 

	}

	public boolean collision() {
		for (int i = 0; i < monde.length; i++) {
			for (int j = 0; j < monde[0].length; j++) {
				if (monde[i][j].getType() != '0' && hitBox.intersects(monde[i][j].getHitBox())) {
					
					blocRencontre = monde[i][j];
					
					if(blocRencontre.getType()=='0' && i == monde.length-1 ) {
						parent.endLevel(false);
					}
					else if (blocRencontre.getType()=='7') {
						pointVie(1);
						parent.miseAJourGrille(i, j);
						break;
					}
					
					
					else if (blocRencontre.getType()=='D') {
						if(!getInvincible()) {
							pointVie(-1);
							setInvincible();
						}
						parent.miseAJourGrille(i, j);
						break;
					}
					else  if (blocRencontre.getType()=='8') {
						parent.pause();
						FenetreInfo fenetre1 = new FenetreInfo(parent.getAstre(),1);
						parent.miseAJourGrille(i, j);
						speedX = 0;
						speedY = 0;
						break;
						
					}
					else if (blocRencontre.getType()=='9') {
						parent.pause();
						FenetreInfo fenetre2 = new FenetreInfo(parent.getAstre(),2);
						parent.miseAJourGrille(i, j);
						break;
					
						
					}
					else if (blocRencontre.getType()=='A') {
						parent.pause();
						FenetreInfo fenetre3 = new FenetreInfo(parent.getAstre(),3);
						parent.miseAJourGrille(i, j);
						break;
						
					}
					else if (blocRencontre.getType()=='B') {
						parent.pause();
						FenetreInfo fenetre4 = new FenetreInfo(parent.getAstre(),4);
						parent.miseAJourGrille(i, j);
						break;
						
					}
					
					else if (blocRencontre.getType()=='C') {
						parent.endLevel(true);
						break;
					}
					
					else if (blocRencontre.getType()=='E') {
						if(!getInvincible()) {
							pointVie(-1);
							setInvincible();
						}
						break;
					}
					
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
		if (speedX < speedMax) {
			acceleration(3, 0);
		}
	}

	public void deplacementGauche() {
		if (speedX > -speedMax) {
			acceleration(-3, 0);
		}
	}

	public void jump() {
		if(onGround) {
			acceleration(0, -15);
			onGround = false;
		}

	}

	public void maj() {
		enVie();
		onGround = checkGround();
		
		// On verifie si le personnage est dans les limites de la carte
		
		if(getY() > monde.length*64-HAUTEUR) {
			pointVie(-pointDeVie);
		}
		
		
		//Verification du sol
		
		if(onGround) {
			if(speedY>0) {

				setY(blocRencontre.getY() - Bloc.getCote() - (HAUTEUR - Bloc.getCote()));
				speedY = 0;

			}	
		}
		else {
			acceleration(0, gravity);
		}
		
		// Verification du bloc frontal
		if(getX() < 0) {
			setX(0);
			speedX = 0;
		}
		
		else if(getX() > (monde[0].length-1)*64) {
			if(getX() > (monde[0].length)*64-HAUTEUR && speedX > 0) {
				setX(monde[0].length*64-LARGEUR);
				speedX = 0;
			}
		}
		
		else if(getX() > 64) {
			if(checkFront()) {
				if(speedX > 0) {
					setX(blocRencontre.getX() - LARGEUR);
					speedX = 0;
				}
			}

			// Verification du bloc arriere

			if(checkBack()) {
				if(speedX < 0) {
					setX(blocRencontre.getX() + Bloc.getCote());
					speedX = 0;
				}
			}
			
			if(checkTop()) {
				if(speedY < 0) {
					setY(blocRencontre.getY()+Bloc.getCote());
					acceleration(0, gravity);
				}
			}
		}
		
		/*if(!collision()) {

			if(speedX < 0 && checkBack()) {
				setX(blocRencontre.getX() + Bloc.getCote());
				speedX = 0;
			}

			else if(speedX > 0 && checkFront()) {
				setX(blocRencontre.getX() - LARGEUR);
				speedX = 0;
			}

		}*/
		/*if (collision()) {
		
			if(speedX < 0 && checkBack()) {
				setX(blocRencontre.getX() + Bloc.getCote());
				speedX = 0;
			}

			else if(speedX > 0 && checkFront()) {
				setX(blocRencontre.getX() - LARGEUR);
				speedX = 0;
			}
			
			else if(speedX > 0) {
				speedX = 0;
			}
			
			else if(speedX < 0) {
				speedX = 0;
			}
			
			//Si le personnage tombe et traverse la case actuelle
			if(onGround) {
				if(speedY>0) {

					setY(blocRencontre.getY() - Bloc.getCote() - (HAUTEUR - Bloc.getCote()));
					//System.out.println("vitesse y = 0");
					speedY = 0;

				}	
				else if(speedY<0) {
					speedY = 0;
				}
			}
			
			move(-speedX,0);
		}*/
		
		
		
		move(speedX, speedY);
		speedX *= FRICTION;
		speedY *= FRICTION;
		
		refreshHB();
		posMonde = positionCourante();
	}
	
	private boolean checkGround() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];
		
		Bloc blocBas = monde[indiceY][indiceX];
		
		if(blocBas != null) {
			if(blocBas.estSol()) {
				return true;
			}
		}
		
		blocBas = monde[indiceY+1][indiceX];
		Rectangle pH = new Rectangle(hitBox);
		pH.y += 1;
		if(blocBas != null) {
			if(blocBas.estSol()) {
				if(pH.intersection(blocBas.getHitBox()).height >0) {
					blocRencontre = blocBas;
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean checkFront() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];
		
		Bloc blocFront = monde[indiceY][indiceX];
		
		if(blocFront != null) {
			if(blocFront.estSol()) {
				return true;
			}
		}
		
		blocFront = monde[indiceY][indiceX+1];
		Rectangle pH = new Rectangle(hitBox);
		pH.x += 1;
		if(blocFront != null) {
			if(blocFront.estSol()){
				if(pH.intersection(blocFront.getHitBox()).width >0) {
					blocRencontre = blocFront;
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean checkTop() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];
		
		Bloc blocTop = monde[indiceY][indiceX];
		
		if(blocTop != null) {
			if(blocTop.estSol()) {
				return true;
			}
		}
		
		blocTop = monde[indiceY-1][indiceX];
		Rectangle pH = new Rectangle(hitBox);
		pH.x += 1;
		if(blocTop != null) {
			if(blocTop.estSol()){
				if(pH.intersection(blocTop.getHitBox()).height >0) {
					blocRencontre = blocTop;
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean checkBack() {
		int indiceX = posMonde[0];
		int indiceY = posMonde[1];
		
		Bloc blocBack = monde[indiceY][indiceX];
		
		if(blocBack != null) {
			if(blocBack.estSol()) {
				return true;
			}
		}
		
		blocBack = monde[indiceY][indiceX-1];
		Rectangle pH = new Rectangle(hitBox);
		pH.x -= 1;
		if(blocBack != null) {
			if(blocBack.estSol()) {
				if(pH.intersection(blocBack.getHitBox()).width >0) {
					blocRencontre = blocBack;
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*public boolean checkFront() {
		Rectangle pH = new Rectangle(hitBox);
		pH.x += 1;
		pH.y -=10;
		if(blocRencontre != null) {
			if(pH.intersection(blocRencontre.getHitBox()).width >0 && pH.intersection(blocRencontre.getHitBox()).height>=pH.intersection(blocRencontre.getHitBox()).width) {
				if(blocRencontre.estSol())
					return true;
			}
			else {
				
				if(posMonde[1] < monde.length && posMonde[0]+1 < monde[0].length && monde[posMonde[1]][posMonde[0]+1].estSol()) {
					if(monde[posMonde[1]][posMonde[0]+1].getHitBox().intersects(pH)) {
						return true;
					}
				}
				
				else if(posMonde[0]+1 > monde[0].length) {
					if(getX()>= monde[0].length*64-LARGEUR)
						return true;
				}
				
				
			}
		}
		
		return false;
	}*/
	
	/*public boolean checkBack() {
		Rectangle pH = new Rectangle(hitBox);
		pH.x -= 1;
		pH.y -=1;
		if(blocRencontre != null) {
			if(pH.intersection(blocRencontre.getHitBox()).width >0 && pH.intersection(blocRencontre.getHitBox()).height>pH.intersection(blocRencontre.getHitBox()).width) {
				if(blocRencontre.estSol())
					return true;
			}
			else {
				
				if(posMonde[1] < monde.length && posMonde[0]-1 > 0 && monde[posMonde[1]][posMonde[0]-1].estSol()) {
					if(monde[posMonde[1]][posMonde[0]-1].getHitBox().intersects(pH)) {
						return true;
					}
				}
				
				else if(posMonde[0]-1 < 0) {
					if(getX()<=0)
						return true;
				}
				
				
			}
		}
		
		return false;
	}*/
	

	private int[] positionCourante() {
		//calcul x
		int x = posX / 64;
		//calcul y
		int y = posY/64+1;
		int[] a = {x,y};
		return a;
		
	}
	
	private void enVie() {
		if(pointDeVie <= 0) {
			enVie = false;
		}
	}

	
	public boolean getEnVie() {
		return enVie;
	}
	

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	public void pointVie(int a) {
		
			if ((a>0) && (pointDeVie<3)){
				pointDeVie=pointDeVie+a;
			}
			if ((a<=0)) {
				pointDeVie=pointDeVie+a;
			}
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == invincibleTimer) {
			invincible = false;
			invincibleTimer.stop();
		}
		
	}
	
	
	
}
