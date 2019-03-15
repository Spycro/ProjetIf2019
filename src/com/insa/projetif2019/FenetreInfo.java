package com.insa.projetif2019;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;

/**
 * 
 * 
 *
 */

w
public class FenetreInfo extends JFrame implements ActionListener, KeyListener{
	
	private JTextArea info;
	private JLabel couverture;
	private JButton Calcul;
	private JTextArea balance; 
	private Planete planete;
	public FenetreInfo(Planete p,int numero) {
		// param�trage de la fenetre
		this.setTitle("A la decouverte de "+p.getNom());
		this.setLayout(null);
		this.setSize(960,640);
		this.setLocation(700,200);
		this.setVisible(true);
		
		

		planete=p;
		
		JLabel couverture = new JLabel(new ImageIcon("bin/Galaxie.jpg"));
		couverture.setBounds(0,0,960,640);
		couverture.setLayout(null);
		

		
		
		// Position bouton
		Calcul = new JButton("Calcul ton poids");
		Calcul.setBounds(100,200,150,50);
		Calcul.addActionListener(this);
		// Position interact
		balance= new JTextArea();
		balance.setBounds(300, 200, 100, 50);
		// Position zone de texte
		info= new JTextArea();
		info.setBounds(10,10,910,600);
		info.setLineWrap(true);
		Font police = new Font(" Arial ",Font.BOLD,13);
		info.setFont(police);
		info.setForeground(Color.white);
		this.add(info);
		switch (numero) {
		case 1: 
			info.setText(p.info1());
			
		break;
		case 2:
			info.setText(p.info2());
			
		break;
		case 3:
			info.setText(p.info3()); 
		break;
		case 4:
			info.setText(p.info4());
			couverture.add(Calcul);
			couverture.add(balance);
		break;
		default:
		break;
		}
		info.setEditable(false);
		info.setOpaque(false);
		
		
		couverture.add(info);
		
		this.add(couverture);
		}
		
	
	
	

@Override
public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource()==Calcul) {
		info.setText("Tu es bien curieux, on redoute tous cette question... Combien indique ta balance? Entre un nombre entier");
		int choix=Integer.parseInt(balance.getText());
		double poid = planete.calculPoids(choix);
		info.setText("Sur"+planete.getNom()+"tu peses"+poid);
	}	
}
	
	

}



