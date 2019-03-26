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

public class FenetreInfo extends JFrame implements ActionListener, KeyListener{
	
	private JTextArea info;
	private JLabel couverture;
	private JButton Calcul;
	private JTextArea balance; 
	private Planete planete;
	private Comete comete;
	private JTextArea annee; 
	private JTextArea age; 

	private JButton Calcul1;
	
	public FenetreInfo(Astre p,int numero) {}
	public void GenererPlaneteInfo() {
		// paramétrage de la fenetre
		this.setTitle("A la decouverte de "+p.getNom());
		this.setLayout(null);
		this.setSize(960,200);
		this.setLocation(700,200);
		this.setVisible(true);
	
		planete=p;
		
		JLabel couverture = new JLabel(new ImageIcon("bin/"+p.getNom()+".planete.jpg"));
		couverture.setBounds(0,0,960,200);
		couverture.setLayout(null);

		// Position bouton
		Calcul = new JButton("Calcul ton poids");
		Calcul.setBounds(100,100,150,50);
		Calcul.addActionListener(this);
		// Position interact
		balance= new JTextArea();
		balance.setBounds(300, 100, 100, 50);
		
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
		repaint();
		}
		
	public FenetreInfo(Comete c, int numero){
		this.setTitle("A la decouverte de "+c.getNom());
		this.setLayout(null);
		this.setSize(960,200);
		this.setLocation(700,200);
		this.setVisible(true);
	
		comete=c;
		JLabel couverture = new JLabel(new ImageIcon("bin/06_b1.jpg"));
		couverture.setBounds(0,0,960,200);
		couverture.setLayout(null);
		
		Calcul1 = new JButton("Quand me verras tu ?");
		Calcul1.setBounds(100,100,200,50);
		Calcul1.addActionListener(this);
		
		// Position interact
		annee= new JTextArea();
		annee.setBounds(400, 100, 100, 50);
		age= new JTextArea();
		age.setBounds(600, 100, 100, 50);
		
		info= new JTextArea();
		info.setBounds(10,10,910,600);
		info.setLineWrap(true);
		Font police = new Font(" Arial ",Font.BOLD,13);
		info.setFont(police);
		info.setForeground(Color.white);
		this.add(info);
		switch (numero) {
		case 1: 
			info.setText(c.info1());
			
		break;
		case 2:
			info.setText(c.info2());
			
		break;
		case 3:
			info.setText(c.info3()); 
		break;
		case 4:
			info.setText(c.info4());
			couverture.add(Calcul1);
			couverture.add(annee);
			couverture.add(age);
		break;
		default:
		break;
		}
		info.setEditable(false);
		info.setOpaque(false);
		
		
		couverture.add(info);
		
		this.add(couverture);
		repaint();
		
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
		int choix=Integer.parseInt(balance.getText());
		int poid = planete.calculPoids(choix);
		info.setText("Sur "+planete.getNom()+" tu peses "+poid+" kg");
	}	
	if (e.getSource()==Calcul1){
		int an=Integer.parseInt(annee.getText());
		int ag =Integer.parseInt(age.getText());
		info.setText(comete.calculProchaineAp(an,ag));
	}
}
	
	

}



