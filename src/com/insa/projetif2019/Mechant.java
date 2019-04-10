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

public class Mechant {
	// ATTRIBUTS
		////////////////////////////////////////////////////////////////////////////////////////
		Image sprite;
		Rectangle hitBox;
		private int posX;
		private int posY;
		private int sens;
		
		private Bloc[][] monde;
	
		private final int LARGEUR = 50;
		private final int HAUTEUR = 75;
		private Personnage joueur;
		
		
		/////////////////////////////////////////////////////////////////////////////////////////

		/// METHODES
		public Mechant(int x, int y, Bloc [][] md, Personnage perso) {
			try {
				sprite = ImageIO.read(new File("bin/ennemi.png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			joueur=perso;
			monde=md;
			posX = x;
			posY = y;
			hitBox = new Rectangle(posX, posY, LARGEUR, HAUTEUR - 20);
			sens=-1;
			
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
		 * Gere le deplacement du mechant 
		 **/

		public void move(double dX, double dY) {

			posX += dX;
			posY += dY;
			/*refreshHB();*/

		}

		/**
		 * permet le dessin du sprite du mechant
		 * 
		 * @param g   objet graphique
		 * @param obs endroit ou sera afficher l'image
		 */

		public void dessineMechant(Graphics g, ImageObserver obs) {
			g.drawImage(sprite, posX, posY, LARGEUR, HAUTEUR, obs);
			/*g.drawRect(posX, posY, hitBox.width, hitBox.height);*/
			
			///////////////////////////////////////////////////////
		}

		/**
		 * Met A jour l'emplacement de la hitbox
		 */
		public void refreshHB() {
			hitBox.setLocation(posX, posY);
		}

		
		
		public boolean collision() {
			if( hitBox.intersects(joueur.getHitbox())) {
				if(joueur.getInvincible()==false) {
						joueur.pointVie(-1);
						joueur.setInvincible();
				}
			}
			for (int i = 0; i < monde.length; i++) {
				for (int j = 0; j < monde[0].length; j++) {
					if (monde[i][j].getType() != '0' && monde[i][j].getType() != '7'&& 
							monde[i][j].getType() != '8' && monde[i][j].getType() != '9' && monde[i][j].getType() != 'A'&&
							monde[i][j].getType() != 'B' && hitBox.intersects(monde[i][j].getHitBox())) {	
						return true;
					}
				}
			}
			return false;
		}
		

		public void actionPerformed(ActionEvent e) {
			
			
		}
		public void vieMechant(){
			if (collision()) {
				sens=-1*sens;
			}
			deplacement(sens);	
		}
		public void deplacement(int sens) {
				if(sens==1) {
					posX+=3;
				}
			
				else {
					posX-=3;
				}
				refreshHB();
			
		}
		
	}


