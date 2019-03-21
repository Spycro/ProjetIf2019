package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauPause extends JPanel {
	JButton bQuit;
	JButton bReturn;
	Image fond;
	
	public PanneauPause() {
		
		setBounds(0,0,960,640);
		setLayout(null);
		
		Font police = new Font("Bookman Old Style",Font.BOLD,30);
		
		JLabel infoPause = new JLabel("PAUSE");
		infoPause.setBounds(425,15,200,100);
		infoPause.setFont(police);
		infoPause.setForeground(Color.black);
		
		bQuit = new JButton("quitter");
		bQuit.setBounds(380,100,200,100);
		bQuit.setBackground(Color.black);
		bQuit.setForeground(Color.red);
		
		bReturn = new JButton("retour");
		bReturn.setBounds(380,200,200,100);
		
		try {
			fond = ImageIO.read(new File("bin/fondPause.jpg"));
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(bQuit);
		add(bReturn);
		add(infoPause);
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fond, 0, 0, 960, 640, this);
		
		
	}
	
}
