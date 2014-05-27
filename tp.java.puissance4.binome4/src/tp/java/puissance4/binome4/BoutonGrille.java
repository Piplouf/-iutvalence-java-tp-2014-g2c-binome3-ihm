package tp.java.puissance4.binome4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoutonGrille extends JButton implements ActionListener,
		MouseListener {

	/** Position du bouton sur la grille */
	private Position position;

	private Fenetre fen;

	private Controleur controleur;

	/** Créer un bouton personnalisé pour la grille */
	public BoutonGrille(Position position, Fenetre fen, Controleur controleur) {
		this.setPreferredSize(new Dimension(50, 50));
		this.position = position;
		this.fen = fen;
		this.controleur = controleur;
		this.initGUI();
		
	}

	/** Affiche le bouton sur l'ihm */
	private void initGUI() {
		this.setBorderPainted(false);
		this.setIcon(new ImageIcon("src/img/pion_blanc_adapt.gif"));
		this.setContentAreaFilled(false);
		this.addActionListener(this);
		this.addMouseListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Position pos = new Position(0, 0);
		// On regarde si l'on peut jouer
		if (!this.controleur.obtenirPlateau().colonneEstPleine(
				Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1)
				&& !this.controleur.obtenirPlateau().recherche4PionsAlignes()) {
			pos = this.controleur.obtenirPlateau().placerPion(
					Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1,
					this.controleur.obtenirCouleurJoueur());
			// On verifie si la colonne est pleine apres, le jeu du joueur si c'est le cas on desactive le bouton colonne
			  if (this.controleur.obtenirPlateau().colonneEstPleine(
			  Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1))
			  	this.fen.rendreInactifBoutonColonne(Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1);
			this.changerCouleur(pos);
			//Si le plateau est plein aucun joueur n'a gagné
			if (this.controleur.obtenirPlateau().estPlein()) {
				this.fen.modifierTexte("Partie terminée aucun gagnant !");
				this.fen.partieGagnee();
			}
			// S'il y a 4 pions alignés le joueur courant a gagné
			if (this.controleur.obtenirPlateau().recherche4PionsAlignes()) {
				this.fen.modifierTexte(this.controleur.obtenirJoueur()
						.obtenirNom() + " remporte la partie");
				this.fen.partieGagnee();
			// Sinon on change le joueur courant, c'est au tour du joueur suivant
			} else {
				this.controleur.changerJoueurCourant();
				this.fen.refresh();
			}
		}

	}

	/** Modifier une case par la couleur du joueur */
	public void changerCouleur(Position pos) {
		if (this.controleur.obtenirJoueurCourant() == 1) {
			fen.modifierCaseGrille(pos, Color.RED);
		} else {
			fen.modifierCaseGrille(pos, Color.YELLOW);
		}
	}

	/** Modifie une case par la couleur du joueur ou la reinitialise 
	 * @param test <br>TRUE : change par la couleur du joueur<br>
	 * FALSE : reinitialise la case*/
	public void changerCouleurQuandJoueurPasseSourisSurBouton(Position pos,
			boolean test) {
		if (this.controleur.obtenirJoueurCourant() == 1) {
			this.fen.modifierCaseGrillePendantUnInstant(pos, Color.RED, test);
		} else {
			this.fen.modifierCaseGrillePendantUnInstant(pos, Color.YELLOW, test);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/** Quand la souris rentre sur le bouton on affiche la position du pion qui va être jouer */
	public void mouseEntered(MouseEvent arg0) {
		Position pos = this.controleur.obtenirPlateau().obtenirPositionDUnPion(
				Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1,
				this.controleur.obtenirCouleurJoueur());
		if (!this.controleur.obtenirPlateau().colonneEstPleine(
				Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1)
				&& !this.controleur.obtenirPlateau().recherche4PionsAlignes())
			this.changerCouleurQuandJoueurPasseSourisSurBouton(pos, true);

	}

	@Override
	/** Quand la souris sort du bouton on enleve l'affichage du pion qui aurait été jouer */
	public void mouseExited(MouseEvent arg0) {
		Position pos = this.controleur.obtenirPlateau().obtenirPositionDUnPion(
				Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1,
				this.controleur.obtenirCouleurJoueur());
		if (!this.controleur.obtenirPlateau().colonneEstPleine(
				Plateau.NOMBRE_COLONNES - this.position.retournerX() - 1)
				&& !this.controleur.obtenirPlateau().recherche4PionsAlignes())
			this.changerCouleurQuandJoueurPasseSourisSurBouton(pos, false);

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
