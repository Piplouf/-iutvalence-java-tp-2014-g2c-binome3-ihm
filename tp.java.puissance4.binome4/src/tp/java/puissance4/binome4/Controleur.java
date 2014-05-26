package tp.java.puissance4.binome4;


/**
 * Class Puissance4
 * 
 * @author massonsilvestre
 * @version V3.2
 * 
 */
public class Controleur {
	private final Joueur[] joueurs;
	private final Plateau plateau;
	private final InterfaceAffichage interfacep4;
	
	private int joueurCourant;

	public Controleur(String joueur1, String joueur2) {
		this.joueurs = new Joueur[] { new Joueur(joueur1, Pion.PION_JAUNE),
				new Joueur(joueur2, Pion.PION_ROUGE) };
		plateau = new Plateau();
		interfacep4 = new InterfaceAffichage(plateau);
	}

	public void nouvellePartie() {
		int vainqueur = -1;
		this.joueurCourant = 0;

		while ((vainqueur == -1) && !plateau.estPlein()) {
			boolean coupValide = true;
			do {
				interfacep4.affichageJoueur(
						joueurs[this.joueurCourant].obtenirNom(),
						joueurs[this.joueurCourant].obtenirCouleur());
			} while (!coupValide);
			if (plateau.estPlein()) {
				vainqueur = -1;
			}
			// Si on a 4 pions alignés, il y a un gagnant et cela meme si le
			// plateau est plein.
			if (plateau.recherche4PionsAlignes()) {
				vainqueur = this.joueurCourant;
			}
		}

		interfacep4.partieTerminee();
		interfacep4.afficher();

		if (vainqueur == -1) {
			interfacep4.matchNul();
		} 
		else {
			interfacep4.nomVainqueur(joueurs[vainqueur].obtenirNom());
		}
	}
	
	public void partieEnCours(){
		int vainqueur = -1;
		this.joueurCourant = 0;

		while ((vainqueur == -1) && !plateau.estPlein()) {
			if (plateau.estPlein()) {
				vainqueur = -1;
			}
			// Si on a 4 pions alignés, il y a un gagnant et cela meme si le
			// plateau est plein.
			if (plateau.recherche4PionsAlignes()) {
				vainqueur = this.joueurCourant;
			}
			// On change de joueur/on change de tour, pour l'itération suivante
		}

		interfacep4.partieTerminee();
		interfacep4.afficher();

		if (vainqueur == -1) {
			interfacep4.matchNul();
		} 
		else {
			interfacep4.nomVainqueur(joueurs[vainqueur].obtenirNom());
		}
	}
	
	public void changerJoueurCourant(){
		if(this.joueurCourant == 0)
			this.joueurCourant = 1;
		else
			this.joueurCourant = 0;
	}
	
	public int obtenirJoueurCourant(){
		return this.joueurCourant;
	}
	
	public Joueur obtenirJoueur(int i){
		return this.joueurs[i];
	}
	
	public Pion obtenirCouleurJoueur(){
		return joueurs[this.joueurCourant].obtenirCouleur();
	}
	
	public Joueur obtenirJoueur(){
		return joueurs[this.joueurCourant];
	}
	public Plateau obtenirPlateau(){
		return this.plateau;
	}
	
	public InterfaceAffichage obtenirInterface(){
		return this.interfacep4;
	}

}
