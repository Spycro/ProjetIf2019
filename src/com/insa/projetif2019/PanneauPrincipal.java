package com.insa.projetif2019;
/**
 * Panneau du projet ou tout se passe
*/


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

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
	private HashSet<Integer> toucheEnfonce; //gerer touche multiple
	
	private Astre niveau;
	
	private ArrayList<Astre> listeAstre;
	
	private Timer tempsDeJeu;
	private Timer enMvt;
	private Timer tempsMechant;
	private Timer tempsEcran;
	boolean enPause = false;
	PanneauPause panneauPause;
	
	private EcranAnnonce annonce;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Constructeur
	 * Ce panneau gere tout les panneaux ou il y aura le menu pause, panneau de jeu etc
	 * @param largeur
	 * @param hauteur
	 * @param listeA Liste des niveaux
	 */
	
	public PanneauPrincipal(int largeur, int hauteur, ArrayList<Astre> listeA) {
		//setBackground(Color.LIGHT_GRAY);
		this.LARGEUR=largeur;
		this.HAUTEUR=hauteur;
		
		setLayout(null);
		
		toucheEnfonce = new HashSet<Integer>(); //utilise pour contenir les touches de mouvement
		enMvt = new Timer(33,this);
		tempsDeJeu = new Timer(33,this);
		tempsMechant=new Timer(20,this);
		tempsEcran = new Timer(1000, this);
		
		niveau = null;
		
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
		
		listeAstre = listeA;
		
		panneauZoneJeu = null;
		
		panneauRules = new PanneauRules();
		panneauRules.setBounds(0, 0, LARGEUR, HAUTEUR);
		panneauRules.bReturn.addActionListener(this);
		
		
	
		
		// definition image fond  
		try {
			imgf = ImageIO.read(new File("bin/Galaxie.jpg"));
		} catch (IOException e) {
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
	
	/**
	 * Lance le jeu après appui du bouton start en generant les panneaux necessaires.
	 */
	
	// Methode gerant le lancement d'un niveau
	public void startGame() {
		
		toucheEnfonce.clear();
		
		// Lecture du fichier de sauvegarde
		niveau = getLevel();
		
		// Cas ou l'astre existe
		if(niveau != null) {
			
			annonce = new EcranAnnonce(niveau, "", false, this);
			
			this.removeAll();
			this.add(annonce);
			repaint();
			tempsEcran.start();
		}
		
		// Cas ou l'astre n'existe pas, cad que le jeu est termine
		else {
			annonce = new EcranAnnonce(null, "Felicitations! \nTu as finis ton exploration avec brio!\nUn jeu cree par Loic, Lucas, Janna et Joanna, 2019", true, this);
			
			this.removeAll();
			this.add(annonce);
		}
	}
	
	public void dispLevel(Astre niveau) {
		
		this.removeAll();
		
		try {
			imgf = ImageIO.read(new File("bin/"+niveau.getNom()+"BG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		repaint();
		
		panneauZoneJeu = new PanneauDeJeu(niveau,this);
		panneauZoneJeu.addKeyListener(this);
		panneauZoneJeu.setBounds(0, 0, LARGEUR, HAUTEUR);
		this.add(panneauZoneJeu);
		panneauZoneJeu.requestFocusInWindow();

		tempsDeJeu.start();
		tempsMechant.start();
	}
	
	// Methode de lecture du fichier de sauvegarde
	
	/**
	 * Charge le fichier file0.sav et lis sont contenu
	 * @return Un Astre 
	 */
	
	
	public Astre getLevel() {
		// Fichier de sauvegarde
		
		File savefile;

		savefile = new File("file0.sav");

		if(!savefile.isFile()) { 
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter("file0.sav"));
				bw.write("Terre");
				bw.close();
				savefile = new File("file0.sav");
			} catch (IOException e) {
				System.out.println("Erreur lors de l'ecriture du fichier de sauvegarde !");
				e.printStackTrace();
			}

		}

		String ligne = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(savefile.getCanonicalPath()));
			ligne = br.readLine();
			
			if(ligne == null || ligne.isEmpty()) {
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new FileWriter(savefile));
					bw.write(listeAstre.get(0).getNom());
					bw.close();
					ligne = listeAstre.get(0).getNom();
				} catch (IOException e) {
					System.out.println("Erreur lors de l'ecriture du fichier de sauvegarde !");
					e.printStackTrace();
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur lors du chargement du fichier de sauvegarde !");
		} 
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		for (int i=0; i<listeAstre.size(); i++) {
	    	if (listeAstre.get(i).getNom().contains(ligne)) {
	    		return listeAstre.get(i);
	    	}		
	    }
		
		return null;
	}
		
	/**
	 * Methode de mise a jour du fichier de sauvegarde
	 */
	
	public void majSave() {
		
		File savefile;
		savefile = new File("file0.sav");
		
		Astre temp = getLevel();
		
		// On vide le fichier de sauvegarde
		PrintWriter pw;
		try {
			pw = new PrintWriter(savefile);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// On va chercher l'astre suivant a charger
		int ind = listeAstre.indexOf(temp);
		
		if (ind < (listeAstre.size()-1)) {
			temp = listeAstre.get(ind+1);
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(savefile));
				bw.write(temp.getNom());
				bw.close();
			} catch (IOException e) {
				System.out.println("Erreur lors de l'ecriture du fichier de sauvegarde");
				e.printStackTrace();
			}
		}
		else {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(savefile));
				bw.write("Game Over !!!");
				bw.close();
			} catch (IOException e) {
				System.out.println("Erreur lors de l'ecriture du fichier de sauvegarde");
				e.printStackTrace();
			}
		}
		
	}
	public void resetSave() {
		File savefile;
		savefile = new File("file0.sav");
		
		// On vide le fichier de sauvegarde
		PrintWriter pw;
		try {
			pw = new PrintWriter(savefile);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(savefile));
			bw.write("Terre");
			bw.close();
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier de sauvegarde");
			e.printStackTrace();
		}			
		
	}
	
	public void setFenMere(JFrame mere) {
		fenMere = mere;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	
		if(e.getSource() == bStart){
			startGame();
		
		}
			
	    if(e.getSource() == bQuit){
	   
	    	fenMere.dispose();
		
	    }
	    
	    if(e.getSource() == enMvt) {
	    	panneauZoneJeu.getJoueur().preMouvement(toucheEnfonce);
	    	
	    }
	    
	    if(e.getSource() == tempsEcran) {
	    	if(niveau != null) {
	    		dispLevel(niveau);
	    	}
	    	tempsEcran.stop();
	    }
	    
	    if(e.getSource() == tempsDeJeu) {
	    	panneauZoneJeu.getJoueur().maj();
	    	if(!panneauZoneJeu.getJoueur().getEnVie()) { //Mort du joueur
	    		stopGame();
	    		this.removeAll();
	    		annonce = new EcranAnnonce(null, "OUPS! Tu es mort. \nRetente ta chance, \nil ne faut jamais s'arreter sur un echec", false, this);
	    		this.add(annonce);
	    		tempsEcran.start();
	    	}
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
        if (e.getSource()==tempsMechant) {
        	for(Mechant mechant :panneauZoneJeu.getMechant()) {
        		mechant.vieMechant();
        	}
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
		
	}	
	
	/**
	 * Methode permettant de mettre le jeu en pause
	 */
	
	public void switchPause() {
		if(!enPause) {
			tempsDeJeu.stop();
			enMvt.stop();
			tempsMechant.stop();
			add(panneauPause);
			remove(panneauZoneJeu);
			panneauPause.grabFocus();
			enPause = !enPause;
			repaint();
		}
		
		else{
			tempsDeJeu.restart();
			enMvt.restart();
			tempsMechant.restart();
			remove(panneauPause);
			add(panneauZoneJeu);
			panneauZoneJeu.grabFocus();
			enPause = !enPause;
			repaint();
			
		}
		
		toucheEnfonce.clear();
	}
	
	/**
	 * Méthode stoppant le jeu
	 */
	
	public void stopGame() {
		tempsDeJeu.stop();
		enMvt.stop();
		removeAll();
	}
	
	


	
	
}
