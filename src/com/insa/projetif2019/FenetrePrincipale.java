package com.insa.projetif2019;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

	/**
	 * Fenetre principale de notre Jeu 
	 * Cette fenetre gere tout nos JPanel et nos buton etc
	 * @author Lucas
	 *
	 */

public class FenetrePrincipale extends JFrame implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;

	private PanneauPrincipal panneauMenuPrincipal;
	
	
	private JButton bStart;
	private JButton bQuit;
	
	private final int LARGEUR = 960; //15 blocs de 64 pixels de large
	private final int HAUTEUR = 640; //10 blocs de 64 pixels de large
	//TOTAL : 15 * 10 = 150 blocs pour la grille 
	
	private int niveauActuel;
	private final int NBNIVEAUX = 5;
	private final int FRAMERATE = 30;

	 
	
	
	public FenetrePrincipale(String niveau){
		super("SusperStar");
		setSize(LARGEUR,HAUTEUR);
		setLocationRelativeTo(null);
		setResizable(false);
	
		
		panneauMenuPrincipal = new PanneauPrincipal(LARGEUR, HAUTEUR, niveau);
		panneauMenuPrincipal.setFenMere(this);
		//panneauMenuPrincipal.afficherNiveau();
		
		
		setContentPane(panneauMenuPrincipal);
		
		niveauActuel =1; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyReleased(KeyEvent e){
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
