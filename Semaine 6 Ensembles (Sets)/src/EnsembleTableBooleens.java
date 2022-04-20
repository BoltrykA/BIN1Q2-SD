
// implementation de l'interface Ensemble via un tableau de booleens

public class EnsembleTableBooleens<E> implements Ensemble<E>{
   
 	private boolean[] table; 
	private int taille;

	// capacite = nombre d'elements de l'univers
	public EnsembleTableBooleens(int capacite){
		table = new boolean[capacite];	
		taille = 0;		
	}

	public int taille(){
		return taille;
	}
	
	public boolean estVide(){
		return taille == 0;
	}

	public boolean contient(E element){
		return table[element.hashCode()];
	}

	public boolean ajouter(E element){
		if (table[element.hashCode()]) return false;
		table[element.hashCode()] = true;
		taille++;
		return true;
	}

	public boolean enlever(E element){
		if (!table[element.hashCode()]) return false;
		table[element.hashCode()] = false;
		taille--;
		return true;
	}

}