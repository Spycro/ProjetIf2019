package com.insa.projetif2019;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Classe bloc provisoire pour charger la carte de test.
 */

public class Bloc{
	
	
	protected static final int COTES = 64; //taille d'un bloc
	protected Color couleur;
	protected int coordX;
	protected int coordY;
	protected String typeBloc;
	public Image sprite;
	Rectangle hitBox;
	
	public Bloc() {
		coordX = 0;
		coordY = 0;
		sprite = null;
		hitBox = new Rectangle();
		typeBloc = "";
		
	}
	
	public Bloc(JPanel parent, int x, int y, char type) {
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
		 */
		coordY = y;
		coordX = x;
		couleur = Color.darkGray;
		hitBox = new Rectangle(y,x,COTES, COTES);
		switch(type){
		
		case '1':
			try {
				sprite = ImageIO.read(new File("bin/BlocTerreSupG.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case '2':
			try {
				sprite = ImageIO.read(new File("bin/BlocTerreSup.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case '3':
			try {
				sprite = ImageIO.read(new File("bin/BlocTerreSupD.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case '4':
			try {
				sprite = ImageIO.read(new File("bin/BlocTerreCoteG.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case '5':
			try {
				sprite = ImageIO.read(new File("bin/BlocTerreInt.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case '6':
			try {
				sprite = ImageIO.read(new File("bin/BlocTerreCoteD.png"));
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
			
		case '9':
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
	
	
	public Color getColor() {
		return couleur;
	}
	
	public void dessine(Graphics g) {
		
	}
	
	
}
