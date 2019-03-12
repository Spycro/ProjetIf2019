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
	
	private JLabel text;
	private Image imgf;
	
	public PanneauRules() {
		
		text=new JLabel();
		text.setBounds(12,56,200,200);
		text.setForeground(Color.white);
		Font police=new Font("Arial",Font.BOLD,20);
		text.setFont(police);
		text.setText("jo le sang");
		
		this.add(text);
		
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
