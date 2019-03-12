package com.insa.projetif2019;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Classe héritant de bloc et de ses attributs et notamment
 * la calsse dessine.
 * @author lucas
 *
 */

public class Sol extends Bloc {

	public Sol(int x, int y) {
		
	}
	
	
	
	public Sol(JPanel parent,int x, int y, char type) {
		super(parent,x,y,type);
	}
	

	@Override
	public void dessine(Graphics g) {
		g.setColor(couleur);
		g.fillRect(coordX, coordY, COTES,COTES);
		
	}

}
