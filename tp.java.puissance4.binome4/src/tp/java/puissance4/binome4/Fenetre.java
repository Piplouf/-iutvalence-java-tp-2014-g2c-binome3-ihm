package tp.java.puissance4.binome4;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Controleur controleur;

	private JButton[][] grille;
	
	private Bouton[] boutons;
	
	private JLabel zoneDeTexte;
	
	private JLabel image;
	
	private JButton rejouer;
	
	private JButton valider;
	
	private JTextField joueur1;
	
	private JTextField joueur2;

	public Fenetre(Controleur controleur) {
		
		this.grille = new JButton[6][7];
		this.boutons = new Bouton[7];
		this.setTitle("Puissance 4");
		this.setSize(390, 530);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.controleur = controleur;
		this.initGUIMenu();
	}
	
	private void initGUIMenu() {
		
		this.joueur1 = new JTextField();
		this.joueur2 = new JTextField();
		JLabel textJ1 = new JLabel("Entrer le nom du joueur 1");
		JLabel textJ2 = new JLabel("Entrer le nom du joueur 2");
		this.valider = new JButton("Valider");
		this.valider.addActionListener(this);
		
		joueur1.setPreferredSize(new Dimension(200,25));
		joueur2.setPreferredSize(new Dimension(200,25));
		
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

	private void initGUIJeu() {
		
		
		this.rejouer = new JButton("Rejouer");
		this.rejouer.setPreferredSize(new Dimension(200,50));
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
	
		this.zoneDeTexte = new JLabel(this.controleur.obtenirJoueur().obtenirNom());
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
								Plateau.NOMBRE_LIGNES - j, Plateau.NOMBRE_COLONNES - i))));
			}
		}
	
		emplacementBoutons.setLayout(new GridLayout(1, 7));
		vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
	
		for (int z = 1; z < Plateau.NOMBRE_COLONNES + 1; z++) {
			emplacementBoutons.add(new JPanel().add(this.boutons[z-1] = new Bouton("↓", 50, 30, z,
					controleur,this)));
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

	public void refresh(){
		this.zoneDeTexte.setText(this.controleur.obtenirJoueur().obtenirNom());
		if(this.controleur.obtenirJoueurCourant() == 0)
			this.image.setIcon(new ImageIcon("src/img/pion_jaune_adapt.gif"));
		else
			this.image.setIcon(new ImageIcon("src/img/pion_rouge_adapt.gif"));
	}
	
	public void partieGagnee(){
		for(int i = 0; i < 7; i++)
			this.boutons[i].setEnabled(false);
		this.rejouer.setVisible(true);
	}
	
	public void modifierCaseGrille(Position pos, Color couleur){
		int y = pos.retournerX();
		int x = Plateau.NOMBRE_LIGNES - 1 - pos.retournerY();
		if(couleur == Color.RED)
			this.grille[x][y].setIcon(new ImageIcon("src/img/pion_rouge_adapt.gif"));
		else
			this.grille[x][y].setIcon(new ImageIcon("src/img/pion_jaune_adapt.gif"));
	}
	
	public void modifierTexte(String texte){
		this.zoneDeTexte.setText(texte);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		JComponent source = (JComponent) event.getSource();
		
		if (source == this.valider){
			if(this.joueur1.getText() == "")
				this.joueur1.setText("Joueur 1");
			if(this.joueur2.getText() == "")
				this.joueur2.setText("Joueur 2");
			this.controleur = new Controleur(this.joueur1.getText(), this.joueur2.getText());
			this.initGUIJeu();
		}
		
		if(source == this.rejouer){
			
			this.setContentPane(new JPanel());
			this.controleur = new Controleur(this.controleur.obtenirJoueur(0).obtenirNom(),this.controleur.obtenirJoueur(1).obtenirNom());
			this.initGUIJeu();
		}
		

		
	}
}