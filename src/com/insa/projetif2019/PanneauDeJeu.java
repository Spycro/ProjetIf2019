package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;


public class PanneauDeJeu extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Bloc[][] grille;
	private Personnage joueur;
	
	private File map;
	private JPanel pCarte;
	private Astre niveau;
	private PanneauPrincipal paternel;
	private ArrayList <Mechant> mechant;

	
	
	public PanneauDeJeu(Astre nomNiveau,PanneauPrincipal p) {
		this.setOpaque(false);
		paternel=p;
		grille = new Bloc[10][90];
		niveau = nomNiveau;
		map = new File("bin/"+niveau.getNom()+".txt");
		pCarte = new JPanel();
		pCarte.setBounds(0, 0, 5760, 640);
		pCarte.setOpaque(false); // Fond transparent
		 mechant = new ArrayList<Mechant>();

	
		this.genererCarte();
		this.add(pCarte);
		
		joueur = new Personnage(50, 250, grille, this);
	}
	
	public void genererCarte() {
		int f=0;
		
		String [] strStore = new String[10];
		BufferedReader br = null;
	    try {
	        String sCurrentLine;
	        br = new BufferedReader(new FileReader(map.getCanonicalPath()));  //file name with path
	        while ((sCurrentLine = br.readLine()) != null) {

	            String strArr = sCurrentLine;
	            
	            strStore[f++] = strArr;
	            
	        }
	    } 
	    catch (IOException e) {
	            e.printStackTrace();
	        } 
	    finally {
	            try {
	                if (br != null)br.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
			
	    
	    
	    int k = 0;
	    for(int i = 0; i<strStore.length;i++) {
	    	for (int j = 0; j<grille[i].length;j++) {
	    		if (strStore[i].charAt(k)!='D') {
	    			grille[i][j] = new Bloc( pCarte, j*64,i*64, strStore[i].charAt(k), niveau.getNom());
	    		}else {
	    			grille[i][j] = new Bloc( pCarte, j*64,i*64, '0', niveau.getNom());
	    			Mechant ennemi=new Mechant (j*64,i*64,grille);
	    			mechant.add(ennemi);
	    		}
	    		k++;
	    	}
	    	k=0;
	    	System.out.println();
	    }
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	

		// Dessin du background (ca marche !!!)
		// Dessine avec l'image "earthBG"
		/*try {

			Image bg = ImageIO.read(new File("bin/earthBG.jpg"));
			g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}*/

		
		currentOffset(g);
		for (int k=0; k<mechant.size(); k++) {
			mechant.get(k).dessineMechant(g, pCarte);
		}
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j].getType() != '0') {
					if (grille[i][j].getType()=='D') {
						
					}else {
						grille[i][j].dessineBloc(g, this);
						//g.drawImage(grille[i][j].sprite, grille[i][j].getX(), grille[i][j].getY(), this);
						/*g.setColor(Color.black);
						g.drawRect(grille[i][j].getX(), grille[i][j].getY(), Bloc.getCote(), Bloc.getCote());*/
					}		
				}
				else {
					/*g.setColor(Color.red);
					g.drawRect(grille[i][j].getX(), grille[i][j].getY(),Bloc.getCote(),Bloc.getCote());*/
				}
			}
		}

		joueur.dessineJoueur(g, pCarte);
		

	}
	public Personnage getJoueur() {
		return joueur;
	}

	public Bloc[][] getGrille() {
		return grille;
	}
	public Astre getAstre() {
		return niveau;
	}
	public ArrayList<Mechant> getMechant() {
		return mechant;
	}
	
	private void currentOffset(Graphics g) {
		if(joueur.getX() > 350 && joueur.getX() < 5150) {
			g.translate(-(joueur.getX() - 350), 0);
		}
		else if(joueur.getX() >= 5150) {
			g.translate(-(5150 - 350), 0);
		}
	}
	public void miseAJourGrille (int i, int j) {
		grille[i][j].setType('0');
		repaint();
	}
	
	public void endLevel(boolean win) {
		
		if(win) {
			paternel.stopGame();
			paternel.majSave();
			paternel.startGame();
		}
		
		else {
			paternel.stopGame();
			paternel.startGame();
		}
	}
	
	public void pause() {
		paternel.switchPause();
	}
	
	public void PanneauMort() {
		
    	
		JTextArea Pmort=new JTextArea();
		Font police1=new Font("Bookman Old Style",Font.BOLD,20);
		Pmort.setBounds(2880, 300, 100, 100);
		Pmort.setForeground(Color.white);
		Pmort.setFont(police1);
		Pmort.setText("OUPS! Tu es mort. Retentes ta chance, il ne faut jamais s'arreter sur un echec");
		Pmort.setEditable(false);
		Pmort.setOpaque(false);
		this.add(Pmort);
		this.remove(pCarte);
		
		
	}
	
    public void PanneauFinJeu() {
		
    	JLabel Fond=new JLabel(); 
    	Fond= new JLabel(new ImageIcon("bin/feu-artifice.jpg"));
    	Fond.setBounds(0,0,960,200);
    	Fond.setLayout(null); 
    	
		JTextArea Pfin=new JTextArea();
		Font police1=new Font("Bookman Old Style",Font.BOLD,20);
		Pfin.setBounds(2880, 300, 100, 100);
		Pfin.setForeground(Color.white);
		Pfin.setFont(police1);
		Pfin.setText("Felicitations! Tu as finis ton exploration avec brio!");
		Pfin.setEditable(false);
		Pfin.setOpaque(false);
		this.add(Fond);
		this.remove(pCarte);
		Fond.add(Pfin);
		
		
	}
    
     public void PanneauPlanete() {
		
    	JLabel Fond=new JLabel(); 
    	Fond= new JLabel(new ImageIcon("bin/"+niveau.getNom()+".planete.jpg"));
    	Fond.setBounds(0,0,this.WIDTH,this.HEIGHT);
    	Fond.setLayout(null); 
 		
		JTextArea Pplanete=new JTextArea();
		Font police1=new Font("Bookman Old Style",Font.BOLD,20);
		Pplanete.setBounds(2880, 300, 100, 100);
		Pplanete.setForeground(Color.white);
		Pplanete.setFont(police1);
		Pplanete.setText(niveau.getNom());
		Pplanete.setEditable(false);
		Pplanete.setOpaque(false);
		this.add(Fond);
		this.remove(pCarte);
		Fond.add(Pplanete);

     }

}
