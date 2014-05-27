package tp.java.puissance4.binome4;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Créer une fenêtre avec deux panels */
public class Fenetre extends JFrame implements ActionListener, Runnable {

	/** */
	private static final long serialVersionUID = 1L;

	/** Prend la partie en parametre */
	private Controleur controleur;

	/** Grille contenant les boutons de la grille */
	private JButton[][] grille;

	/** Tableau contenant les boutons permettant d'ajouter les pions */
	private Bouton[] boutons;

	/** Zone de texte affichant le nom du joueur et les messages */
	private JLabel zoneDeTexte;

	/** Image contenant le pion en cours */
	private JLabel image;

	private JButton rejouer;

	private JButton valider;

	private JTextField joueur1;

	private JTextField joueur2;

	/** Constructeur par défaut de la fenêtre */
	public Fenetre(Controleur controleur) {

		this.grille = new JButton[6][7];
		this.boutons = new Bouton[7];
		this.setTitle("Puissance 4");
		this.setSize(390, 530);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.controleur = controleur;
	}

	/** Initialise le menu demandant les noms des joueurs */
	private void initGUIMenu() {

		this.joueur1 = new JTextField();
		this.joueur2 = new JTextField();
		JLabel textJ1 = new JLabel("Entrer le nom du joueur 1");
		JLabel textJ2 = new JLabel("Entrer le nom du joueur 2");
		this.valider = new JButton("Valider");
		this.valider.addActionListener(this);

		joueur1.setPreferredSize(new Dimension(200, 25));
		joueur2.setPreferredSize(new Dimension(200, 25));

		JPanel vertical = new JPanel();
		JPanel tJ1 = new JPanel();
		JPanel tJ2 = new JPanel();
		JPanel jou1 = new JPanel();
		JPanel jou2 = new JPanel();
		JPanel bouton = new JPanel();

		tJ1.add(textJ1);
		jou1.add(joueur1);
		tJ2.add(textJ2);
		jou2.add(joueur2);
		bouton.add(this.valider);

		vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

		vertical.add(tJ1);
		vertical.add(jou1);
		vertical.add(tJ2);
		vertical.add(jou2);
		vertical.add(bouton);

		JPanel global = new JPanel();
		global.add(vertical);

		this.add(global);
		this.setVisible(true);

	}

	/** Initialise l'interface du jeu */
	private void initGUIJeu() {

		this.rejouer = new JButton("Rejouer");
		this.rejouer.setPreferredSize(new Dimension(200, 50));
		this.rejouer.setVisible(false);
		this.rejouer.addActionListener(this);

		JPanel boutonRejouer = new JPanel();
		boutonRejouer.add(this.rejouer);

		JPanel partie = new JPanel();
		JPanel texte = new JPanel();

		JPanel vertical = new JPanel();
		JPanel emplacementBoutons = new JPanel();
		JPanel grille = new JPanel();

		JPanel zoneTexte = new JPanel();
		JPanel zoneImage = new JPanel();

		this.zoneDeTexte = new JLabel(this.controleur.obtenirJoueur()
				.obtenirNom());
		this.image = new JLabel();
		this.image.setIcon(new ImageIcon("src/img/pion_jaune_adapt.gif"));

		zoneDeTexte.setPreferredSize(new Dimension(200, 50));
		image.setPreferredSize(new Dimension(50, 50));

		texte.setLayout(new BoxLayout(texte, BoxLayout.LINE_AXIS));
		zoneTexte.add(zoneDeTexte);
		zoneImage.add(image);
		texte.add(zoneTexte);
		texte.add(zoneImage);
		grille.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		grille.setLayout(new GridLayout(6, 7));
		for (int i = 0; i < Plateau.NOMBRE_LIGNES; i++) {
			for (int j = 0; j < Plateau.NOMBRE_COLONNES; j++) {
				grille.add(new JPanel()
						.add(this.grille[i][j] = new BoutonGrille(new Position(
								Plateau.NOMBRE_LIGNES - j,
								Plateau.NOMBRE_COLONNES - i),this, this.controleur)));
			}
		}

		emplacementBoutons.setLayout(new GridLayout(1, 7));
		vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

		for (int z = 1; z < Plateau.NOMBRE_COLONNES + 1; z++) {
			emplacementBoutons.add(new JPanel()
					.add(this.boutons[z - 1] = new Bouton("↓", 50, 30, z,
							controleur, this)));
		}

		vertical.add(texte);
		vertical.add(emplacementBoutons);
		vertical.add(new JLabel("\n"));
		vertical.add(grille);
		vertical.add(boutonRejouer);

		partie.add(vertical);
		this.setContentPane(partie);
		this.setVisible(true);

	}

	/** Reinitialise l'affichage de l'interface */
	public void refresh() {
		this.zoneDeTexte.setText(this.controleur.obtenirJoueur().obtenirNom());
		if (this.controleur.obtenirJoueurCourant() == 0)
			this.image.setIcon(new ImageIcon("src/img/pion_jaune_adapt.gif"));
		else
			this.image.setIcon(new ImageIcon("src/img/pion_rouge_adapt.gif"));
	}

	/** Bloque le jeu, et active la possibilité de rejouer car un jouer a gagne */
	public void partieGagnee() {
		for (int i = 0; i < 7; i++)
			this.boutons[i].setEnabled(false);
		for(Position posi : this.controleur.obtenirPlateau().obtenirPositionsPionsAlignes())
			this.modifierCaseGrilleVictoire(posi);
		this.rejouer.setVisible(true);
	}
	
	/** Bloque le jeu, et active la possibilité de rejouer car la partie est nulle*/
	public void partieNulle(){
		for (int i = 0; i < 7; i++)
			this.boutons[i].setEnabled(false);
		this.rejouer.setVisible(true);
	}

	/** Permet d'afficher les cases qui ont permis de remporter la victoire */
	private void modifierCaseGrilleVictoire(Position pos) {
		int y = pos.retournerX();
		int x = Plateau.NOMBRE_LIGNES - 1 - pos.retournerY();
		
		if(this.controleur.obtenirJoueurCourant() == 0)
			this.grille[x][y].setIcon(new ImageIcon("src/img/pion_jaune_adapt_2.gif"));
		else
			this.grille[x][y].setIcon(new ImageIcon("src/img/pion_rouge_adapt_2.gif"));
	}

	/** Affiche les cases qui sont remplies */
	public void modifierCaseGrille(Position pos, Color couleur) {
		int y = pos.retournerX();
		int x = Plateau.NOMBRE_LIGNES - 1 - pos.retournerY();
		if (couleur == Color.RED)
			this.grille[x][y].setIcon(new ImageIcon(
					"src/img/pion_rouge_adapt.gif"));
		else
			this.grille[x][y].setIcon(new ImageIcon(
					"src/img/pion_jaune_adapt.gif"));
	}

	/** Affiche les cases que l'on va remplir si l'on joue dans cette colonne*/
	public void modifierCaseGrillePendantUnInstant(Position pos, Color couleur,
			boolean test) {
		int y = pos.retournerX();
		int x = Plateau.NOMBRE_LIGNES - 1 - pos.retournerY();
		if (test) {

			if (couleur == Color.RED) {
				this.grille[x][y].setIcon(new ImageIcon(
						"src/img/pion_rouge_adapt_1.gif"));
			} else {
				this.grille[x][y].setIcon(new ImageIcon(
						"src/img/pion_jaune_adapt_1.gif"));
			}
		} else
			this.grille[x][y].setIcon(new ImageIcon(
					"src/img/pion_blanc_adapt.gif"));
	}
	
	
	/** Permet de rendre inactif les boutons colonnes */
	public void rendreInactifBoutonColonne(int colonne){
		this.boutons[colonne].setEnabled(false);
	}

	/** Affiche le texte mis en parametre dans la zone de texte en haut */
	public void modifierTexte(String texte) {
		this.zoneDeTexte.setText(texte);
	}

	@Override
	/** Capte l'evenement quand on clique sur l'ihm */
	public void actionPerformed(ActionEvent event) {

		JComponent source = (JComponent) event.getSource();

		// Si on clique sur valider on lance le jeu
		if (source == this.valider) {
			this.controleur = new Controleur(this.joueur1.getText(),
					this.joueur2.getText());
			this.initGUIJeu();
		}

		//Si on clique sur rejouer on relance le jeu
		if (source == this.rejouer) {

			this.setContentPane(new JPanel());
			this.controleur = new Controleur(this.controleur.obtenirJoueur(0)
					.obtenirNom(), this.controleur.obtenirJoueur(1)
					.obtenirNom());
			this.initGUIJeu();
		}
	}

	@Override
	/** Méthode qui se lance à la creation du Thread */
	public void run() {
		this.initGUIMenu();

	}
}