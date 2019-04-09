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
		
		private PanneauDeJeu parent; 
		private boolean onGround;
		private int[] posMonde;

		private Bloc[][] monde;
		private Bloc blocRencontre;

		private final int LARGEUR = 40;
		private final int HAUTEUR = 75;
		boolean enVie = true;		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////

		/// METHODES
		public Mechant(int x, int y) {
			try {
				sprite = ImageIO.read(new File("bin/ennemi.png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			posX = x;
			posY = y;
			hitBox = new Rectangle(posX, posY, LARGEUR, HAUTEUR - 20);
			parent= null;
			enVie = true;
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
			refreshHB();

		}

		/**
		 * permet le dessin du sprite du mechant
		 * 
		 * @param g   objet graphique
		 * @param obs endroit ou sera afficher l'image
		 */

		public void dessineMechant(Graphics g, ImageObserver obs) {
			g.drawImage(sprite, posX, posY, LARGEUR, HAUTEUR, obs);
			g.drawRect(posX, posY, hitBox.width, hitBox.height);
			
			///////////////////////////////////////////////////////
			
			if(blocRencontre != null) {
				g.setColor(Color.white);
				g.fillRect(blocRencontre.getX(), blocRencontre.getY(), Bloc.getCote(), Bloc.getCote());
			}
			if(posX > 350)
				g.translate(posX-350, 0);
		}

		/**
		 * Met A jour l'emplacement de la hitbox
		 */
		public void refreshHB() {
			hitBox.setLocation(posX, posY);
		}

		
		public boolean collision() {
			for (int i = 0; i < monde.length; i++) {
				for (int j = 0; j < monde[0].length; j++) {
					if (monde[i][j].getType() != '0' && hitBox.intersects(monde[i][j].getHitBox())) {
						move(0,5);				
						blocRencontre = monde[i][j];
						return true;
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
		
	
		public boolean getEnVie() {
			return enVie;
		}

		public void actionPerformed(ActionEvent e) {
			
			
		}
		
		public void deplacement(boolean direction) {
			if(direction) {
				posX+=3;
			}
			
			else {
				posY-=3;
			}
		}
		
	}


