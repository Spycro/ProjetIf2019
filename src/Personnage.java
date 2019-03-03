import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/**
 * Personnage joueur
 * @author Lucas
 *
 */


public class Personnage {
	
	
	Image sprite;
	Rectangle hitBox;
	private int x;
	private int y;
	
	private final int dX = 1;
	
	private final int LARGEUR = 40;
	private final int HAUTEUR = 75;
	
	public Personnage() {
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = 0;
		y = 0;
		hitBox = new Rectangle(x,y,LARGEUR, HAUTEUR);
	}
	
	public Personnage(int pX, int pY) {
		//création du personnage
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		x= pX;
		y = pY;
		hitBox = new Rectangle(pX,pY, LARGEUR, HAUTEUR);
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public void setX(int pX) {x = pX;}
	
	public void setY(int pY) {y = pY;}
	
	public void move(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (key) {
		case 39: x += dX; break ;
		case 37: x-= dX; break;
		}
	}
	
	
	
	public void dessineJoueur(Graphics g, ImageObserver obs) {
		g.drawImage(sprite, x, y, LARGEUR, HAUTEUR, obs);
	}
}
