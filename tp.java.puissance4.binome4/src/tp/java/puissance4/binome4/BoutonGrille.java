package tp.java.puissance4.binome4;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoutonGrille extends JButton {

	private Position position;
	
	public BoutonGrille(Position position){
		this.setPreferredSize(new Dimension(50,50));
		this.position = position;
		this.setBorderPainted(false);
		this.setIcon(new ImageIcon("src/img/pion_blanc_adapt.gif"));
	}
}
