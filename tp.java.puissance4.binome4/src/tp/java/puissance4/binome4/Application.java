package tp.java.puissance4.binome4;

import javax.swing.SwingUtilities;

/**
 * Class Application
 *
 * @author massonsilvestre
 * @version V3.2
 */
public class Application {
	public static void main(String[] args) {
		
		
		Controleur controleur = new Controleur("","");
		Fenetre fenetre = new Fenetre(controleur);
		SwingUtilities.invokeLater(fenetre);
		}
}

		
