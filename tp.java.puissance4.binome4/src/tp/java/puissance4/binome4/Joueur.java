package tp.java.puissance4.binome4;

/**
 * Class Joueur
 *
 * @author massonsilvestre
 * @version V3.2
 */

public class Joueur {
	/** Variable nom du joueur. */
    private final String nom;
    /** Couleur asignée au joueur */
    private final Pion couleur;

    /** Constructeur joueur, définissant un joueur
     * par son nom et sa couleur.
     */
    public Joueur(String nom, Pion couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    /** méthode obtenirNom() permettant d'obtenir le nom
     * du joueur
     * @return nom
     */
    public String obtenirNom() {
        return nom;
    }

    /**méthode obtenirCouleur() permettant d'obtenir
     * la couleur du joueur 
     * @return couleur
     */
    public Pion obtenirCouleur() {
        return couleur;
    }
}
