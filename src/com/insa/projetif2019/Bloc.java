package com.insa.projetif2019;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 
 * 
 *Faire les sols et les objets 
 * avec lesquels on pourra interragir comme les pics ou les points 
 * d'interrogations.
 * 
 */
/**
 * astre : Astre pour lequel on demande un bloc
 * type : type de bloc demande
 * Description type :
 * 1 - coin superieur gauche
 * 2 - sol superieur plat
 * 3 - coin superieur droit
 * 4 - bloc cote gauche
 * 5 - bloc interieur
 * 6 - bloc cote droit
 * 7 - bloc coeur
 * 8 - bloc info 1
 * 9 - bloc info 2
 * A - bloc info 3
 * B - bloc info 4
 * C - bloc fin
 * D - ennemi
 * E - Pics
 * 0 - vide
 */
public class Bloc  {
	
	private static final int COTES = 64; //taille d'un bloc
	private int coordX;
	private int coordY;
	private char typeBloc; 
	private Image sprite;
	private Rectangle hitBox;
	private JPanel papa; 


	
	/**
	 * Constructeur des blocs
	 * Les blocs initialise dependent des valeurs lues dans les fichiers
	 * de carte de jeu
	 * Ceux-ci sont colorise en fonction du monde actuel
	 * @param parent : JPanel invoquant ce bloc (Panneau de Jeu)
	 * @param x
	 * @param y
	 * @param type Cf ci-dessus
	 * @param astre permet colorisation des blocs.
	 */
	
	public Bloc( JPanel parent, int x, int y, char type, String astre) {
		papa=parent; 
		coordY = y;
		coordX = x;
		hitBox = new Rectangle(x,y,COTES, COTES);
		typeBloc=type; 
		generationBloc(astre, parent);
		
	}	

	public char getType() {
		return typeBloc;
	}
	public int getX () {
		return coordX;
	}
	public int getY() {
		return coordY;
	}
	public void setType (char c) {
		typeBloc=c;
	}
	public static int getCote() {
		return COTES;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	/**
	 * 
	 * @return true si bloc de collision false si non
	 */
	
	public boolean estSol() {
		if(getType() == '1' || getType() == '2' || getType() == '3' || getType() == '4' || getType() == '5' || getType() == '6' || getType() == 'E') {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param nom Astre permettant la couleur
	 * @param parent
	 */
	
	public void generationBloc (String nom, JPanel parent) {
		
			switch (typeBloc) {
			case '0':
				sprite = null;
				hitBox = new Rectangle(coordX,coordY,0,0);
			break;
			case '1':
				try {
					sprite = ImageIO.read(new File("bin/Bloc"+nom+"SupG.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break; 
			case '2':
				try {
					sprite = ImageIO.read(new File("bin/Bloc"+nom+"Sup.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;	
			case '3':
				try {
					sprite = ImageIO.read(new File("bin/Bloc"+nom+"SupD.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case '4':
				try {
					sprite = ImageIO.read(new File("bin/Bloc"+nom+"CoteG.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case '5':
				try {
					sprite = ImageIO.read(new File("bin/Bloc"+nom+"Int.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case '6':
				try {
					sprite = ImageIO.read(new File("bin/Bloc"+nom+"CoteD.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case '7': 
				try {
					sprite = ImageIO.read(new File("bin/heart.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case '8': 
				try {
					sprite = ImageIO.read(new File("bin/infobox1.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case '9': 
				try {
					sprite = ImageIO.read(new File("bin/infobox2.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case 'A': 
				try {
					sprite = ImageIO.read(new File("bin/infobox3.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case 'B': 
				try {
					sprite = ImageIO.read(new File("bin/infobox4.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			case 'C':
				try {
					sprite = ImageIO.read(new File("bin/rocket.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
			
			case 'D':
							
			break;
			
			case 'E':
				try {
					sprite = ImageIO.read(new File("bin/pics.png"));
					coordY-=1;
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;	
			
		default:
			String err = "Erreur lors du chargement du bloc. Le type : " + this.typeBloc + " n'existe pas !";
			JOptionPane.showMessageDialog(parent, err);
			break;
		}
	}
	
	public void setCoord(int x, int y){
		coordX = x;
		coordY = y;
	}
	
	/**
	 * 
	 * @param g Objet graphique du JPanel invoque.
	 * @param obs
	 */
	
	public void dessineBloc(Graphics g, ImageObserver obs) {
		g.drawImage(sprite, coordX, coordY, COTES, COTES, obs);
	}

	
}
