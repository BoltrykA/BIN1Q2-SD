
import java.util.HashSet;


// METTEZ VOS NOMS ET PRENOMS!!!!

public class ListeCaracteres {

	private NoeudCaractere tete;
	// N'ajoutez pas d'autres attributs
	
	
	public ListeCaracteres() {
		this.tete=null;
		// N'ajoutez pas de noeud sentinelle!
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public ListeCaracteres(char[] tableCaracteres) {
		for (int i = tableCaracteres.length-1; i>=0; i--) {
			this.tete=new NoeudCaractere(tableCaracteres[i],tete);
		}	
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){	
		return toString(tete);
	}
	// A NE PAS MODIFIER --> POUR LES TESTS!!!	
	private String toString(NoeudCaractere noeud) {
		if(noeud == null)
			return "";
		return " " + noeud.caractere + toString(noeud.suivant);
	}

	/**
	 * insere un nouveaud noeud soit en debut de liste, soit dans la liste, soit en fin
	 * @param position la position du nouveau noeud
	 * @param caractere le caractere place dans le nouveau noeud
	 * @throws IllegalArgumentException si la position passee en parametre est invalide
	 */
	public void inserer(int position, char caractere) throws IllegalArgumentException{
		if (position < 0) throw new IllegalArgumentException();
		int i = 0;
		NoeudCaractere noeudPrecedent = null;
		NoeudCaractere noeud = tete;
		while (noeud != null && i < position){
			noeudPrecedent = noeud;
			noeud = noeud.suivant;
			i++;
		}
		if (position > i) throw new IllegalArgumentException();
		NoeudCaractere node = new NoeudCaractere(caractere,noeud);
		if (noeudPrecedent == null) tete = node;
		else noeudPrecedent.suivant = node;
	}

	
	/**
	 * verifie la presence de caracteres identiques dans la liste
	 * @return true si la liste contient au moins 2 x un meme caractere, false sinon
	 */
	public boolean contientExAequos(){
		// TODO
		//des indications sur l'implementation a suivre se trouvent dans le questionnaire d'examen 
		return false;
	}
	
	
	/**
	 * calcule le nombre de fois qu'apparait le caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return le nombre d'occurrences du caractere
	 */
	public int nombreOccurrences(char caractereRecherche){
		return nombreOccurrences(tete,caractereRecherche);
	}
	
	private int nombreOccurrences(NoeudCaractere noeud, char caractereRecherche) {
		// methode recursive
		if(noeud == null)
			return 0;
		if(noeud.caractere==caractereRecherche)
			return 1 + nombreOccurrences(noeud.suivant,caractereRecherche);
		return nombreOccurrences(noeud.suivant, caractereRecherche);
	}

	/**
	 * verifie la presence du caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return true si le caractere est present dans la liste, false sinon
	 */
	public boolean contient(char caractereRecherche){
		//des indications sur l'implementation a suivre se trouvent dans le questionnaire d'examen 
		// TODO
		return false;
	}
	
	
	/**
	 * calcule combien de caracteres de la liste sont contenus dans la table passee en parametre
	 * Si un caractere se trouve plusieurs X dans la liste et dans la table, il est compte autant de X qu'il apparait dans la liste
	 * Par exemple liste : b b z b b k b  table : b u b --> 5X
	 * @param tableCaracteresRecherches la table des caracteres recherches
	 * @return le nombre de caracteres de la liste contenus dans la table
	 * @throws IllegalArgumentException
	 */
	public int nombreCaracteresTable(char[] tableCaracteresRecherches){
		if(tableCaracteresRecherches==null||tableCaracteresRecherches.length==0)
			throw new IllegalArgumentException();
		//des indications sur l'implementation a suivre se trouvent dans le questionnaire d'examen 
		// TODO
		return 0;
	}


	private class NoeudCaractere{
		private char caractere;
		private NoeudCaractere suivant;

		public NoeudCaractere(char caractere, NoeudCaractere suivant){
			this.caractere = caractere;
			this.suivant = suivant;
		}

	}
}
