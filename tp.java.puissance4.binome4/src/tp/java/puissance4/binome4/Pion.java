package tp.java.puissance4.binome4;

/**
 * Class Pion
 * 
 * @author massonsilvestre
 * @version V3.2
 */

public enum Pion {

	CASE_VIDE(" . ", 0), /** La case est vide. */
	PION_JAUNE(" \u25CF ", 1), /** Le pion jaune, valeur : 1. */
	PION_ROUGE(" \u25CB ", 2);/** Le pion jaune, valeur : 2. */
	
	/** chaine de caract�re repr�sentation. */
	private final String representation;
	
	/** valeur est de type entier num�rique. */
	private final int valeur;

	Pion(String representation, int valeur) {
		this.representation = representation;
		this.valeur = valeur;
	}

	public int valeur() {
		return this.valeur;
	}

	@Override
	public String toString() {
		return this.representation;
	}
}
