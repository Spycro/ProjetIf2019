import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class FenetrePrincipale extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panneauMenuPrincipal;
	private JPanel panneauZoneJeu; //sera peut être un tableau de zone de jeu
	
	private JButton bStart;
	private JButton bQuit;
	
	private final int LARGEUR = 960; //15 blocs de 64 pixels de large
	private final int HAUTEUR = 640; //10 blocs de 64 pixels de large
	//TOTAL : 15 * 10 = 150 blocs pour la grille 
	
	
	public FenetrePrincipale(){
		super("SusperStar");
		setSize(LARGEUR,HAUTEUR);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panneauMenuPrincipal = new PanneauPrincipal();
		panneauZoneJeu = new PanneauDeJeu();
		
		panneauMenuPrincipal.setLayout(null);
		panneauZoneJeu.setBounds(0, 0, LARGEUR, HAUTEUR);
		
		bStart = new JButton("Start");
		bStart.setBounds(300,500,100,50);
		bStart.addActionListener(this);
		panneauMenuPrincipal.add(bStart);
		
		bQuit = new JButton("Quit");
		bQuit.setBounds(300,500,100,50);
		//bQuit.addActionListener(this);
		
		//panneauMenuPrincipal.add(panneauZoneJeu);
		
		
		setContentPane(panneauMenuPrincipal);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Principe de Start (add dans actionPerformed avec remove() du JButton)
		 */
		
		if(e.getSource() == bStart){
			System.out.println("Button Pressed");
			panneauMenuPrincipal.add(panneauZoneJeu);
			this.getContentPane().remove(bStart);
			this.add(bQuit);
		}
		
		this.getContentPane().repaint();
		
	}



}
