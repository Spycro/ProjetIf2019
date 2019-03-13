package com.insa.projetif2019;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
 * @author Lucas, Loic, Janna
 * 
 */
/**
 * astre : Astre pour lequel on demande un bloc
 * type : type de bloc demand�
 * Description type :
 * 1 - coin sup�rieur gauche
 * 2 - sol sup�rieur plat
 * 3 - coin sup�rieur droit
 * 4 - bloc cot� gauche
 * 5 - bloc int�rieur
 * 6 - bloc cot� droit
 * 7: bloc coeur
 * 8:bloc info
 * 9: bloc fin
 * 0: vide
 */
public class Bloc  {
	
	private static final int COTES = 64; //taille d'un bloc
	private int coordX;
	private int coordY;
	private char typeBloc; 
	Image sprite;
	Rectangle hitBox;
	
	public Bloc(JPanel parent, int x, int y, char type, String astre) {

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
	public static int getcote() {
		return COTES;
	}
	public void generationBloc (String nom, JPanel parent) {
		
			switch (typeBloc) {
			case '0':
				sprite = null;
				hitBox = new Rectangle();
			break;
			case '1':
				try {
					sprite = ImageIO.read(new File("bin/bloc"+nom+"SupG.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break; 
			case '2':
				try {
					sprite = ImageIO.read(new File("bin/bloc"+nom+"Sup.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;	
			case '3':
				try {
					sprite = ImageIO.read(new File("bin/bloc"+nom+"SupD.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case '4':
				try {
					sprite = ImageIO.read(new File("bin/bloc"+nom+"CoteG.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case '5':
				try {
					sprite = ImageIO.read(new File("bin/bloc"+nom+"Int.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case '6':
				try {
					sprite = ImageIO.read(new File("bin/bloc"+nom+"CoteD.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case '7': 
				try {
					sprite = ImageIO.read(new File("bin/heart.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case '8': 
				try {
					sprite = ImageIO.read(new File("bin/infobox.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case '9': // image a changer +nom
				try {
					sprite = ImageIO.read(new File("bin/heart.png"));
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;		
		default:
			String err = "Erreur lors du chargement du bloc. Le type : " + this.typeBloc + " n'existe pas !";
			JOptionPane.showMessageDialog(parent, err);
			break;
		}
	}
	
}
