package com.insa.projetif2019;
/**
 * Panneau du projet où tout se passe
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
	private JTextArea text;
	
	private PanneauDeJeu panneauZoneJeu; //sera peut etre un tableau de zone de jeu
	private PanneauRules panneauRules;
	private final int LARGEUR;
	private final int HAUTEUR;
	private Set<Integer> toucheEnfonce; //gerer touche multiple
	
	protected Astre niveau;
	
	Timer tempsDeJeu;
	Timer enMvt;
	
	boolean enPause = false;
	PanneauPause panneauPause;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public PanneauPrincipal(int largeur, int hauteur, Astre niveauACharger) {
		//setBackground(Color.LIGHT_GRAY);
		this.LARGEUR=largeur;
		this.HAUTEUR=hauteur;
		
		setLayout(null);
		
		toucheEnfonce = new HashSet<Integer>(); //utilise pour contenir les touches de mouvement
		enMvt = new Timer(33,this);
		tempsDeJeu = new Timer(33,this);
		
		//definition panneau Pause
		panneauPause = new PanneauPause();
		panneauPause.addKeyListener(this);
		panneauPause.bQuit.addActionListener(this);
		panneauPause.bReturn.addActionListener(this);

		
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
		
		niveau = niveauACharger;
		
		panneauZoneJeu = new PanneauDeJeu(niveau);
		panneauZoneJeu.addKeyListener(this);
		panneauZoneJeu.setBounds(0, 0, LARGEUR, HAUTEUR);
		
		panneauRules = new PanneauRules();
		panneauRules.setBounds(0, 0, LARGEUR, HAUTEUR);
		panneauRules.bReturn.addActionListener(this);
		
		
	
		
		// definition image fond  
		try {
			imgf = ImageIO.read(new File("bin/Galaxie.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		text= new JTextArea();
		text.setBounds(5,30,960,130);
		text.setForeground(Color.white);
		Font police1=new Font("Bookman Old Style",Font.BOLD,35);
		text.setFont(police1);
		text.setText("Bonjour jeune explorateur! Nous sommes ravis de\nconstater que tu t'interesses a notre merveilleuse\ngalaxie!");
		text.setEditable(false);
		text.setOpaque(false);
		this.add(text);
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawImage(imgf, 0, 0, this.getWidth(), this.getHeight(), this);

		
	}
	
	public void setFenMere(JFrame mere) {
		fenMere = mere;
	}
	/*
	public void afficherNiveau() {
		panneauZoneJeu = new PanneauDeJeu(niveau);
		panneauZoneJeu.addKeyListener(this);
		panneauZoneJeu.setBounds(0, 0, LARGEUR, HAUTEUR);
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		
	
		if(e.getSource() == bStart){
			
			System.out.println("Button Pressed");
			this.add(panneauZoneJeu);
			panneauZoneJeu.requestFocusInWindow();
			
			this.remove(bStart);
			this.remove(bQuit);;
			this.remove(bComtJouer);
			remove(text);
			
			tempsDeJeu.start();
		
		}
			
	    if(e.getSource() == bQuit){
	   
	    	fenMere.dispose();
		
	    }
	    
	    if(e.getSource() == enMvt) {
	    	panneauZoneJeu.getJoueur().preMouvement(toucheEnfonce);
	    	
	    }
	    if(e.getSource() == tempsDeJeu) {
	    	panneauZoneJeu.getJoueur().maj();
	    	if(!panneauZoneJeu.getJoueur().getEnVie())
	    		stopGame();
	    }
	    
        if(e.getSource() == bComtJouer){
			
			System.out.println("Button Pressed");
			add(panneauRules);
			panneauRules.grabFocus();
			
			this.remove(bStart);
			this.remove(bQuit);;
			this.remove(bComtJouer);
			this.remove(text);
			
			
        }	
        
        
        
        if(e.getSource() == panneauPause.bReturn) {
        	switchPause();
        }
        if(e.getSource() == panneauPause.bQuit) {
        	fenMere.dispose();
        }
        
        if(e.getSource() == panneauRules.bReturn) {
        	this.remove(panneauRules);
        	grabFocus();
        	this.add(bStart);
			this.add(bQuit);;
			this.add(bComtJouer);
			this.add(text);
        	
        	
        }
        
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getSource() == panneauZoneJeu) {
			toucheEnfonce.add(e.getKeyCode()); 
			enMvt.start();

		}
		repaint();
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		toucheEnfonce.remove(e.getKeyCode());
		if(toucheEnfonce.isEmpty()) {
			enMvt.stop();
		}
		
		
		if(e.getKeyCode() == 27){
			switchPause();
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
	/**
	 * Methode permettant de mettre le jeu en pause
	 */
	
	private void switchPause() {
		if(!enPause) {
			tempsDeJeu.stop();
			enMvt.stop();
			add(panneauPause);
			remove(panneauZoneJeu);
			panneauPause.grabFocus();
			enPause = !enPause;
			repaint();
		}
		
		else{
			tempsDeJeu.restart();
			enMvt.restart();
			remove(panneauPause);
			add(panneauZoneJeu);
			panneauZoneJeu.grabFocus();
			enPause = !enPause;
			repaint();
			
		}
	}
	
	private void stopGame() {
		
	}


	
	
}
