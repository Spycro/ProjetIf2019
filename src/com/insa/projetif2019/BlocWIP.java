package com.insa.projetif2019;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * 
 * 
 * Classe Abstraite Bloc utilise pour faire les sols et les objets 
 * avec lesquels on pourra interragir comme les pics ou les points 
 * d'interrogations.
 * @author Lucas, Lo�c
 * 
 */

public abstract class BlocWIP {
	
	
	protected static final int COTES = 64; //taille d'un bloc
	protected Color couleur;
	protected int coordX;
	protected int coordY;
	protected String typeBloc;
	Image sprite;
	Rectangle hitBox;
	
	
	
	public Bloc() {
		couleur = Color.darkGray;
		hitBox = new Rectangle();
	}
	
	public Bloc(JFrame parent, int x, int y, int type, String astre) {
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
		hitBox = new Rectangle(x,y,COTES, COTES);
		switch(astre){
		case "terre":
			try {
				sprite = ImageIO.read(new File("bin/terre/blocTerre.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "mars":
			try {
				sprite = ImageIO.read(new File("bin/mars/blocMars.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "venus":
			try {
				sprite = ImageIO.read(new File("bin/venus/blocVenus.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "mercure":
			try {
				sprite = ImageIO.read(new File("bin/mercure/blocMercure.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "jupiter":
			try {
				sprite = ImageIO.read(new File("bin/jupiter/blocJupiter.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "saturne":
			try {
				sprite = ImageIO.read(new File("bin/saturne/blocSaturne.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "uranus":
			try {
				sprite = ImageIO.read(new File("bin/uranus/blocUranus.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "neptune":
			try {
				sprite = ImageIO.read(new File("bin/neptune/blocNeptune.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "soleil":
			try {
				sprite = ImageIO.read(new File("bin/soleil/blocSoleil.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "lune":
			try {
				sprite = ImageIO.read(new File("bin/lune/blocLune.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "comete":
			try {
				sprite = ImageIO.read(new File("bin/comete/blocComete.png"));
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
	
	public Bloc(int x, int y, Color c) {
		coordY = y;
		coordX = x;
		couleur = c;
		hitBox = new Rectangle(x,y,COTES, COTES);
	}
	
	public Color getColor() {
		return couleur;
	}
	
	public abstract void dessine(Graphics g);
	
	
}
