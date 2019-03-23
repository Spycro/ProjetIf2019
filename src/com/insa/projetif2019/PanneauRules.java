package com.insa.projetif2019;


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;

public class PanneauRules extends JPanel {
	
	private JTextArea text1;
	private JTextArea text2;
	private JTextArea text3;
	private JTextArea text4;
	private JTextArea text5;
	private Image imgf1;
	private Image imgf2;
	
	public PanneauRules() {
		
		text1= new JTextArea();
		text1.setBounds(5,5,960,100);
		text1.setForeground(Color.white);
		Font police1=new Font("Bookman Old Style",Font.BOLD,18);
		text1.setFont(police1);
		text1.setText("Pour devenir un astronaute confirme, lis attentivement les regles du jeu!\n"
				+ "Tu vas pouvoir te balader de planete en planete muni de ton equipement d'astronaute!\n"
				+ "C'est tres simple :");
		text1.setEditable(false);
		text1.setOpaque(false);
		this.add(text1);
		
		text2=new JTextArea();
		text2.setBounds(410,80,100,50);
		text2.setForeground(Color.white);
		text2.setFont(police1);
		text2.setText("SAUTER");
		text2.setEditable(false);
		text2.setOpaque(false);
		this.add(text2);  
		
		text3=new JTextArea();
		text3.setBounds(260,180,100,50);
		text3.setForeground(Color.white);
		text3.setFont(police1);
		text3.setText("RECULER");
		text3.setEditable(false);
		text3.setOpaque(false);
		this.add(text3);  
		
		text4=new JTextArea();
		text4.setBounds(570,180,100,50);
		text4.setForeground(Color.white);
		text4.setFont(police1);
		text4.setText("AVANCER");
		text4.setEditable(false);
		text4.setOpaque(false);
		this.add(text4);  
		
		text5=new JTextArea();
		text5.setBounds(5,325,960,300);
		text5.setForeground(Color.white);
		text5.setFont(police1);
		text5.setText("A chaque rencontre de planete, tu pourras partir l'explorer avec passion...\n"
				+ "Tu pars en exploration avec 3 vies, mais quel chanceux!\n"
				+ "Chaque rencontre avec un coeur te procure une vie, mais prends garde a ceux qui te veulent du mal\n"
				+ "ils n'hesiteront pas a te les reprendre! Enfin tu pourras apprendre des choses... magnifique non?\n"
				+ "Tu sais tout sur cette planete? Repars en expedition et passes a la suivante! ");
		text5.setEditable(false);
		text5.setOpaque(false);
		this.add(text5);  
		
		
		// definition image fond  
		try {
		    imgf1 = ImageIO.read(new File("bin/Galaxie.jpg"));
		} catch (IOException e) {
		   // TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//d�finition image fl�che 
		try {
		    imgf2 = ImageIO.read(new File("bin/fleche.png"));
		} catch (IOException e) {
		   // TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
   public void paintComponent(Graphics g) {
		
	  super.paintComponent(g);
		
	  g.drawImage(imgf1,0,0,this.getWidth(),this.getHeight(),this);
	  
	  g.drawImage(imgf2,360,110,200,120,this);
	  
	  
	
   }
	
	

}
