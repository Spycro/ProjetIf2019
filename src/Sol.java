import java.awt.Graphics;

/**
 * Classe héritant de bloc et de ses attributs et notamment
 * la calsse dessine.
 * @author lucas
 *
 */

public class Sol extends Bloc {

	public Sol(int x, int y) {
		super(x,y);
	}

	@Override
	public void dessine(Graphics g) {
		g.setColor(couleur);
		g.fillRect(coordX, coordY, COTES,COTES);
		
	}

}
