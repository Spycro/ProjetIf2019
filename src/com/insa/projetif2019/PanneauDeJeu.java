package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PanneauDeJeu extends JPanel {

	/**
	 * Panneau ou se passe tout le jeu se trouve le joueur, la grille de jeu etc..
	 */

	private static final long serialVersionUID = 1L;

	private Bloc[][] grille;
	private Personnage joueur;

	private File map;
	private JPanel pCarte;
	private Astre niveau;
	private PanneauPrincipal parent;
	private ArrayList<Mechant> mechant;

	/**
	 * Constructeur
	 * 
	 * @param nomNiveau
	 * @param p         Panneau parent pour mis à jour de terrain de jeu
	 */

	public PanneauDeJeu(Astre nomNiveau, PanneauPrincipal p) {
		this.setOpaque(false);
		parent = p;
		grille = new Bloc[10][90];
		niveau = nomNiveau;
		map = new File("bin/" + niveau.getNom() + ".txt");
		pCarte = new JPanel();
		pCarte.setBounds(0, 0, 5760, 640);
		pCarte.setOpaque(false); // Fond transparent
		mechant = new ArrayList<Mechant>();

		joueur = new Personnage(50, 250, grille, this);

		this.genererCarte();
		this.add(pCarte);
	}

	/**
	 * Generation a pratir des fichiers textes en ressources
	 */

	public void genererCarte() {
		int f = 0;

		String[] strStore = new String[10];
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(map.getCanonicalPath())); // file name with path
			while ((sCurrentLine = br.readLine()) != null) {

				String strArr = sCurrentLine;

				strStore[f++] = strArr;

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		int k = 0;
		for (int i = 0; i < strStore.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (strStore[i].charAt(k) != 'D') {
					grille[i][j] = new Bloc(pCarte, j * 64, i * 64, strStore[i].charAt(k), niveau.getNom());
				} else {
					grille[i][j] = new Bloc(pCarte, j * 64, i * 64, '0', niveau.getNom());
					Mechant ennemi = new Mechant(j * 64, i * 64, grille, joueur);
					mechant.add(ennemi);
				}
				k++;
			}
			k = 0;
			System.out.println();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		currentOffset(g);
		for (int k = 0; k < mechant.size(); k++) {
			mechant.get(k).dessineMechant(g, pCarte);
		}
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j].getType() != '0') {
					grille[i][j].dessineBloc(g, this);
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

	/**
	 * Modifie l'origine de l'objet graphique pour une translation de la carte de
	 * jeu
	 * 
	 * @param g
	 */

	private void currentOffset(Graphics g) {
		if (joueur.getX() > 350 && joueur.getX() < 5150) {
			g.translate(-(joueur.getX() - 350), 0);
		} else if (joueur.getX() >= 5150) {
			g.translate(-(5150 - 350), 0);
		}
	}

	/**
	 * Met a jour le bloc de la carte (efface une infoBox ou un coeur)
	 * 
	 * @param i x
	 * @param j y
	 */

	public void miseAJourGrille(int i, int j) {
		grille[i][j].setType('0');
		repaint();
	}

	/**
	 * valide apres la prise de l'objet de fin de niveau
	 * 
	 * @param win
	 */

	public void endLevel(boolean win) {

		if (win) {
			parent.stopGame();
			parent.majSave();
			parent.startGame();
		}

		else {
			parent.stopGame();
			parent.startGame();
		}
	}

	public void pause() {
		parent.switchPause();
	}

	public void PanneauMort() {

		JTextArea Pmort = new JTextArea();
		Font police1 = new Font("Bookman Old Style", Font.BOLD, 20);
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

		JLabel Fond = new JLabel();
		Fond = new JLabel(new ImageIcon("bin/feu-artifice.jpg"));
		Fond.setBounds(0, 0, 960, 200);
		Fond.setLayout(null);

		JTextArea Pfin = new JTextArea();
		Font police1 = new Font("Bookman Old Style", Font.BOLD, 20);
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

		JLabel Fond = new JLabel();
		Fond = new JLabel(new ImageIcon("bin/" + niveau.getNom() + ".planete.jpg"));
		Fond.setBounds(0, 0, this.getWidth(), this.getHeight());
		Fond.setLayout(null);

		JTextArea Pplanete = new JTextArea();
		Font police1 = new Font("Bookman Old Style", Font.BOLD, 20);
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
