package com.insa.projetif2019;
/**
 * Menu principal du jeu. Permet de lancer le jeu ou de le quitter
*/


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class PanneauPrincipal extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JButton bStart;
	private JButton bQuit;
	private JButton bComtJouer;
	
	
	private PanneauDeJeu panneauZoneJeu; //sera peut etre un tableau de zone de jeu
	private final int LARGEUR;
	private final int HAUTEUR;
	private Set<Integer> toucheEnfonce; //gerer touche multiple

	public PanneauPrincipal(int largeur, int hauteur) {
		setBackground(Color.LIGHT_GRAY);
		this.LARGEUR=largeur;
		this.HAUTEUR=hauteur;
		
		setLayout(null);
		
		toucheEnfonce = new HashSet<Integer>();

		
		//Définition du bouton de lancement
		bStart = new JButton("Start");
		bStart.setBounds(300,500,100,50);
		bStart.addActionListener(this);
		this.add(bStart);
		
		//Définition du bouton pour quitter
		bQuit = new JButton("Exit");
		bQuit.setBounds(560,500,100,50);
		bQuit.addActionListener(this);
		this.add(bQuit);
		
		//Définition du bouton pour apprendre à jouer 
		bComtJouer=new JButton("Comment jouer");
		bComtJouer.setBounds(450,560,100,50);
		bComtJouer.addActionListener(this);
		this.add(bComtJouer);
		
		panneauZoneJeu = new PanneauDeJeu();
		panneauZoneJeu.addKeyListener(this);
		panneauZoneJeu.setBounds(0, 0, LARGEUR, HAUTEUR);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	
		if(e.getSource() == bStart){
			
			System.out.println("Button Pressed");
			this.add(panneauZoneJeu);
			panneauZoneJeu.requestFocusInWindow();
			
			this.remove(bStart);
			this.remove(bQuit);;
			this.remove(bComtJouer);
			
	    if(e.getSource() == bQuit){
	   
	    //	frameToClose.dispose();
	    	
	    }
			
		
		}
		
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getSource() == panneauZoneJeu) {
			toucheEnfonce.add(e.getKeyCode()); 
			System.out.println(toucheEnfonce.size());
			panneauZoneJeu.getJoueur().move(e); //a modifier totalement sur le principe
		}
		this.repaint();
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		toucheEnfonce.remove(e.getKeyCode());
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	


	
	
}
