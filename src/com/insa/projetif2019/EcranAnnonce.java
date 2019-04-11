package com.insa.projetif2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EcranAnnonce extends JPanel implements ActionListener {

	// Attributs
	private JTextArea tText;
	private Font police;
	private Image imgf;
	private JButton recommencer; 
	private PanneauPrincipal ancetre;
	// Methodes
	public EcranAnnonce(Astre niveau, String text, boolean win, PanneauPrincipal parent) {
		ancetre=parent;
		// Chargement de la police
		police = new Font("Bookman Old Style", Font.BOLD, 35);

		tText = new JTextArea();
		tText.setForeground(Color.white);
		tText.setFont(police);
		tText.setEditable(false);
		tText.setOpaque(false);
		
		recommencer = new JButton("Recommencer");
		recommencer.setBounds(100,500,150,50);
		recommencer.addActionListener(this);

		if (niveau != null) {
			tText.setText(niveau.getNom());
			tText.setBounds((parent.getWidth()-tText.getText().length()*40) / 2, parent.getHeight() / 2, 40*tText.getText().length(), 200);
			try {
				imgf = ImageIO.read(new File("bin/" + niveau.getNom() + "BG.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		else{
			if(win) {
				try {
					imgf = ImageIO.read(new File("bin/feu-artifice.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.add(recommencer);
			}
			else {
				this.setBackground(Color.black);
			}
			tText.setText(text);
			tText.setBounds(0, parent.getHeight()/18, 40*tText.getText().length(), parent.getHeight());
		}
		
		
		this.setBounds(0,0, parent.getWidth(), parent.getHeight());
		this.add(tText);
	
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(imgf, 0, 0, this.getWidth(), this.getHeight(), this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==recommencer) {
			ancetre.stopGame();
			ancetre.resetSave();
			ancetre.startGame();
		}
		// TODO Auto-generated method stub
		
	}

}
