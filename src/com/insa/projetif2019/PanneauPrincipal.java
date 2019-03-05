package com.insa.projetif2019;
/**
 * Menu principal du jeu. Permet de lancer le jeu ou de le quitter
*/


import java.awt.*;
import javax.swing.*;

public class PanneauPrincipal extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton bStart;
	private JButton bQuit;

	public PanneauPrincipal() {
		setBackground(Color.LIGHT_GRAY);
		//Définition du bouton de lancement
		bStart = new JButton();
		
		//Définition du bouton pour quitter
		bQuit = new JButton();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		
	}


	
	
}
