/**
 * classe heritant de JPanel qui gerera probablement le menu principal.
 * implementera surement ActionListener pour les jButton qui seront present. 
*/


import java.awt.*;

import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// grille (pas dans le bon panel sachant que celuiu ci est le menu principale juste qqs tests)
	private Bloc[][] grille;

	public PanneauPrincipal() {
		setBackground(Color.blue);
		grille = new Bloc[12][12];
		
		
		//remplissage de la grille de Sols
		for(int i = 0;i<grille.length;i++) {
			for(int j = 0; j<grille.length;j++) {
				grille[i][j] = new Sol(i*50,j*50);
				
			}
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Dessin  du background (ca marche !!!)
		for(int i = 0;i<grille.length;i++) {
			for(int j = 0; j<grille.length;j++) {
				grille[i][j].dessine(g);
				
			}
		}
		
		
	}
	
}
