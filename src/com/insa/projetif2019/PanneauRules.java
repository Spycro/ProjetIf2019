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
	private JLabel text2;
	private Image imgf;
	
	public PanneauRules() {
		
		text1= new JTextArea();
		text1.setBounds(5,5,960,300);
		text1.setForeground(Color.white);
		Font police1=new Font("Arial",Font.BOLD,30);
		text1.setFont(police1);
		text1.setText("Bonjour jeune explorateur! Nous sommes ravis de constater que\ntu t'intéresses à notre merveilleuse galaxie!");
		text1.setEditable(false);
		text1.setOpaque(false);
		this.add(text1);
		
		text2=new JLabel();
		text2.setBounds(5,300,750,200);
		text2.setForeground(Color.white);
		Font police2=new Font("Arial",Font.BOLD,30);
		text2.setFont(police2);
		text2.setText("");
		
		this.add(text2);
		
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
	
	

}
