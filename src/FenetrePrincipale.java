import java.awt.Color;
import java.awt.GraphicsConfiguration;


import javax.swing.*;


public class FenetrePrincipale extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel pan;
	
	private final int LARGEUR = 900;
	private final int LONGUEUR = 600;

	public FenetrePrincipale(){
		super("SusperStar");
		
		
		setSize(LARGEUR,LONGUEUR);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pan = new PanneauPrincipal();
		setBackground(Color.blue);
		
		setContentPane(pan);
		
		
		setVisible(true);
	
	
	}



}
