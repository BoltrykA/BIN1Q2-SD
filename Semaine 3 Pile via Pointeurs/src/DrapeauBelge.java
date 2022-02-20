/**
 * @author Alicia Boltryk
 */
public class DrapeauBelge {
	
	private NoeudCouleur premierNoir;	
	private NoeudCouleur dernierJaune;
	// NE PAS AJOUTER D'AUTRES ATTRIBUTS!!!
	
	/**
	 * construit une chaine contenant 3 noeuds avec les caracteres 'n', 'j' et 'r' (dans cet ordre)
	 */
	public DrapeauBelge() {
		NoeudCouleur noeud3 = new NoeudCouleur('r');
		NoeudCouleur noeud2 = new NoeudCouleur('j',noeud3);
		NoeudCouleur noeud1 = new NoeudCouleur('n',noeud2);
		premierNoir = noeud1;
		dernierJaune = noeud2;
	}

	// A NE PAS MODIFIER. VA SERVIR POUR LES TESTS
	public String toString(){
		String drapeau="";
		NoeudCouleur baladeur = premierNoir;
		while(baladeur!=null){
			drapeau+=baladeur.couleur;
			baladeur = baladeur.suivant;
		}
		return drapeau;
	}
	
	/**
	 * ajoute un noeud avec la couleur passee en parametre dans la chaine
	 * La chaine doit respecter les couleurs du  drapeau belge : noir/jaune/rouge
	 * @param couleur un caractere representant une couleur du drapeau belge : 'n', 'j' ou 'r'
	 * @throws IllegalArgumentException si le caractere ne correspond pas a un des 3 caracteres : 'n', 'j' ou 'r'
	 */
	public void ajouter(char couleur){
		if (couleur != 'n' && couleur != 'j' && couleur != 'r') throw new IllegalArgumentException();
		if (couleur == 'n'){
			NoeudCouleur ex = premierNoir;
			premierNoir = new NoeudCouleur(couleur,ex);
		}
		else if (couleur == 'j'){
			NoeudCouleur nvNoeud = new NoeudCouleur(couleur, dernierJaune.suivant);
			dernierJaune.suivant = nvNoeud;
			dernierJaune = nvNoeud;
		}
		else {
			NoeudCouleur elem = dernierJaune.suivant;
			dernierJaune.suivant = new NoeudCouleur(couleur, elem);
		}
	}
	
	private class NoeudCouleur{
		
		private char couleur;
		private NoeudCouleur suivant;
		
		private NoeudCouleur(char couleur){
			this.couleur = couleur;
			this.suivant = null;
		}
		
		private NoeudCouleur(char couleur, NoeudCouleur suivant){
			this.couleur = couleur;
			this.suivant = suivant;
		}

	}
}
