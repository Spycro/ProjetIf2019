import java.awt.*;


/**
 * 
 * 
 * Classe Abstraite Bloc utilise pour faire les sols et les objets 
 * avec lesquels on pourra interragir comme les pics ou les points 
 * d'interrogations.
 * @author Lucas
 * 
 */

public abstract class Bloc {
	
	
	protected final int COTES = 64;
	protected Color couleur;
	protected int coordX;
	protected int coordY;
	
	
	
	public Bloc() {
		couleur = Color.darkGray;
	}
	
	public Bloc(int x, int y) {
		coordY = y;
		coordX = x;
		couleur = Color.darkGray;
	}
	
	public Bloc(int x, int y, Color c) {
		coordY = y;
		coordX = x;
		couleur = c;
	}
	
	public Color getColor() {
		return couleur;
	}
	
	public abstract void dessine(Graphics g);
	
	
}
