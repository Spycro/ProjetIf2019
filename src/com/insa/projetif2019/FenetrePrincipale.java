package com.insa.projetif2019;

import java.util.*;
import javax.swing.*;

	/**
	 * Fenetre principale de notre Jeu 
	 * Cette fenetre gere tout nos JPanel et nos buton etc
	 * @author Lucas
	 *
	 */

public class FenetrePrincipale extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private PanneauPrincipal panneauMenuPrincipal;
	
	
	/*private JButton bStart;
	private JButton bQuit;*/ //TODO A virer je crois ?
	
	private final int LARGEUR = 960;
	private final int HAUTEUR = 640; 
	
	
	public FenetrePrincipale(ArrayList<Astre> listeAstre){
		super("SuperStar");
		setSize(LARGEUR,HAUTEUR);
		setLocationRelativeTo(null);
		setResizable(false);
	
		
		panneauMenuPrincipal = new PanneauPrincipal(LARGEUR, HAUTEUR, listeAstre);
		panneauMenuPrincipal.setFenMere(this);
		//panneauMenuPrincipal.afficherNiveau();
		
		
		setContentPane(panneauMenuPrincipal);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}




}
