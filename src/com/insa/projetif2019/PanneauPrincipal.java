package com.insa.projetif2019;
/**
 * Menu principal du jeu. Permet de lancer le jeu ou de le quitter
*/


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauPrincipal extends JPanel implements ActionListener, KeyListener{

	//ATTRIBUTS
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final long serialVersionUID = 1L;
	
	private static JFrame fenMere;
	
	private JButton bStart;
	private JButton bQuit;
	private JButton bComtJouer;
	
	private Image imgf;
	
	private PanneauDeJeu panneauZoneJeu; //sera peut etre un tableau de zone de jeu
	private PanneauRules panneauRules;
	private final int LARGEUR;
	private final int HAUTEUR;
	private Set<Integer> toucheEnfonce; //gerer touche multiple
	Timer tempsDeJeu;
	Timer enMvt;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public PanneauPrincipal(int largeur, int hauteur) {
		//setBackground(Color.LIGHT_GRAY);
		this.LARGEUR=largeur;
		this.HAUTEUR=hauteur;
		
		setLayout(null);
		
		toucheEnfonce = new HashSet<Integer>();
		enMvt = new Timer(33,this);
		tempsDeJeu = new Timer(33,this);
	

		
		//Definition du bouton de lancement
		bStart = new JButton("Start");
		bStart.setBounds(300,500,100,50);
		bStart.addActionListener(this);
		this.add(bStart);
		
		//Definition du bouton pour quitter
		bQuit = new JButton("Exit");
		bQuit.setBounds(560,500,100,50);
		bQuit.addActionListener(this);
		this.add(bQuit);
		
		//Definition du bouton pour apprendre a jouer 
		bComtJouer=new JButton("How to play");
		bComtJouer.setBounds(427,550,110,50);
		bComtJouer.addActionListener(this);
		this.add(bComtJouer);
		
		panneauZoneJeu = new PanneauDeJeu("level");
		panneauZoneJeu.addKeyListener(this);
		panneauZoneJeu.setBounds(0, 0, LARGEUR, HAUTEUR);
		
		panneauRules = new PanneauRules();
		panneauRules.setBounds(0, 0, LARGEUR, HAUTEUR);
		

		
		// definition image fond  
		try {
			imgf = ImageIO.read(new File("bin/Galaxie.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawImage(imgf, 0, 0, this.getWidth(), this.getHeight(), this);

		
	}
	
	public void setFenMere(JFrame mere) {
		fenMere = mere;
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
			
			tempsDeJeu.start();
		
		}
			
	    if(e.getSource() == bQuit){
	   
	    	fenMere.dispose();
		
	    }
	    
	    if(e.getSource() == enMvt) {
	    	panneauZoneJeu.getJoueur().preMouvement(toucheEnfonce);
	    }
	    if(e.getSource() == tempsDeJeu) 
	    	panneauZoneJeu.getJoueur().maj();
	    
        if(e.getSource() == bComtJouer){
			
			System.out.println("Button Pressed");
			this.add(panneauRules);
			panneauRules.requestFocusInWindow();
			
			this.remove(bStart);
			this.remove(bQuit);;
			this.remove(bComtJouer);
			
        }	
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getSource() == panneauZoneJeu) {
			toucheEnfonce.add(e.getKeyCode()); 
			enMvt.start();
		}
		this.repaint();
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		toucheEnfonce.remove(e.getKeyCode());
		if(toucheEnfonce.isEmpty()) {
			enMvt.stop();
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	


	
	
}
