package tp.java.puissance4.binome4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Bouton extends JButton implements ActionListener{

	/** Numero du bouton pour la colonne*/
	private int numero;

	private Controleur controleur;

	private Fenetre fen;

	/** Créer un bouton colonne */
	public Bouton() {
		this.setPreferredSize(new Dimension(50, 50));
		this.numero = 0;
		this.controleur = new Controleur("", "");
	}

	public Bouton(String name, int x, int y, int num, Controleur controleur,
			final Fenetre fen) {
		super(name);
		this.setPreferredSize(new Dimension(x, y));
		this.numero = num;
		this.controleur = controleur;
		this.fen = fen;
		this.addActionListener(this);

	}

	public Bouton obtenirBouton() {
		return this;
	}

	public Controleur obtenirControleur() {
		return this.controleur;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Position pos = new Position(0, 0);
		if (!obtenirControleur().obtenirPlateau().colonneEstPleine(numero - 1)) {
			pos = obtenirControleur().obtenirPlateau().placerPion(numero - 1,
					obtenirControleur().obtenirCouleurJoueur());
			if (obtenirControleur().obtenirPlateau().colonneEstPleine(
					numero - 1))
				obtenirBouton().setEnabled(false);
			this.changerCouleur(pos);
			if (this.controleur.obtenirPlateau().estPlein()) {
				this.fen.modifierTexte("Partie terminée aucun gagnant !");
				this.fen.partieGagnee();
			}
			if (this.controleur.obtenirPlateau().recherche4PionsAlignes()) {
				this.fen.modifierTexte(this.controleur.obtenirJoueur()
						.obtenirNom() + " remporte la partie");
				this.fen.partieGagnee();
			} else {
				this.controleur.changerJoueurCourant();
				this.fen.refresh();
			}
		}
	}

	/** Change la couleur du bouton selon le joueur courant */
	public void changerCouleur(Position pos) {
		if (this.controleur.obtenirJoueurCourant() == 1) {
			fen.modifierCaseGrille(pos, Color.RED);
		} else {
			fen.modifierCaseGrille(pos, Color.YELLOW);
		}
	}

}
