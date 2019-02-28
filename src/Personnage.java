import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Personnage joueur
 * @author Lucas
 *
 */


public class Personnage {
	
	
	Image sprite;
	
	public Personnage() {
		try {
			sprite = ImageIO.read(new File("bin/astronaut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dessineJoueur(Graphics g, ImageObserver obs) {
		g.drawImage(sprite, 100, 50, obs);
	}
}
