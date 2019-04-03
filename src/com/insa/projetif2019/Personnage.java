package com.insa.projetif2019;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import java.awt.Rectangle;


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
					
					if(monde[i][j].getType()=='0' && i == monde.length-1 ) {
						parent.endLevel(false);
					}
					else if ( monde[i][j].getType()=='7') {
						pointVie(1);
						parent.miseAJourGrille(i, j);
						break;
					}
					
					
					else if ( monde[i][j].getType()=='D') {
						pointVie(-1);
						parent.miseAJourGrille(i, j);
						break;
					}
					else  if (monde[i][j].getType()=='8') {
						parent.pause();
						FenetreInfo fenetre1 = new FenetreInfo(parent.getAstre(),1);
						parent.miseAJourGrille(i, j);
						speedX = 0;
						speedY = 0;
						break;
						
					}
					else if (monde[i][j].getType()=='9') {
						parent.pause();
						FenetreInfo fenetre2 = new FenetreInfo(parent.getAstre(),2);
						parent.miseAJourGrille(i, j);
						break;
					
						
					}
					else if (monde[i][j].getType()=='A') {
						parent.pause();
						FenetreInfo fenetre3 = new FenetreInfo(parent.getAstre(),3);
						parent.miseAJourGrille(i, j);
						break;
						
					}
					else if (monde[i][j].getType()=='B') {
						parent.pause();
						FenetreInfo fenetre4 = new FenetreInfo(parent.getAstre(),4);
						parent.miseAJourGrille(i, j);
						break;
						
					}
					
					else if (monde[i][j].getType()=='C') {
						parent.endLevel(true);
						break;
					}
					
					else if (monde[i][j].getType()=='E') {
						pointVie(-1);
						break;
					}
					
					blocRencontre = monde[i][j];
					
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
		
		if(!onGround) 
			acceleration(0, gravity);
		
		enVie();
		onGround = checkGround();
		
		if(getY() > parent.getHeight()-HAUTEUR) {
			pointVie(-pointDeVie);
		}
		
		if (collision()) {
			
			if(speedX < 0 && checkBack()) {
				setX(blocRencontre.getX() + Bloc.getCote()); //+ (LARGEUR - Bloc.getCote()));
				speedX = 0;
			}
			
			else if(speedX > 0 && checkFront()) {
				setX(blocRencontre.getX() - Bloc.getCote() - (LARGEUR - Bloc.getCote()));
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
		}
		
		
		
		move(speedX, speedY);
		speedX *= FRICTION;
		speedY *= FRICTION;
		
		refreshHB();
		posMonde = positionCourante();
	}
	
	private boolean checkGround() {
		Rectangle pH = new Rectangle(hitBox);
		pH.y += 1;
		if(blocRencontre != null) {
			//System.out.println(pH.intersection(solCourant.hitBox));
			if(blocRencontre.estSol()) {
				if(pH.intersection(blocRencontre.getHitBox()).height >0 && pH.intersection(blocRencontre.getHitBox()).width >0 && pH.intersection(blocRencontre.getHitBox()).width<64) {
					return true;
				}
				else {

					if(posMonde[1]+1 < monde.length && posMonde[0] < monde[0].length && monde[posMonde[1]+1][posMonde[0]].estSol()) {
						if(monde[posMonde[1]+1][posMonde[0]].getHitBox().intersects(pH)) {
							return true;
						}
					}

				}
			}
		}
		
		return false;
	}
	
	public boolean checkFront() {
		Rectangle pH = new Rectangle(hitBox);
		pH.x += 1;
		pH.y -=10;
		if(blocRencontre != null) {
			//System.out.println(pH.intersection(solCourant.hitBox));
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
				
				
			}
		}
		
		return false;
	}
	
	public boolean checkBack() {
		Rectangle pH = new Rectangle(hitBox);
		pH.x -= 1;
		pH.y -=1;
		if(blocRencontre != null) {
			//System.out.println(pH.intersection(solCourant.hitBox));
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
				
				
			}
		}
		
		return false;
	}
	

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
	
	
	
}
