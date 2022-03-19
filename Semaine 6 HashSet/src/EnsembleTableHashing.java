/**
 * implementation de l'interface Ensemble via une table de listes
 * @author Alicia Boltryk
 *
 */

public class EnsembleTableHashing<E> implements Ensemble<E>{
	
	private ListeSimpleImpl<E>[] tableListes;
	private int taille;
	private double loadFactor;  //pour le defi 

	// taille : taille logique 
	// capacite : taille physique 
	public EnsembleTableHashing(int capacite){	
		tableListes = new ListeSimpleImpl[capacite];
		for (int i = 0; i < capacite; i++) {
			tableListes[i] = new ListeSimpleImpl<>();
		}
		
	}
	
	// taille : taille logique 
	// capacite : taille physique 
	public EnsembleTableHashing(int capacite, double loadFactor){	
		// pour le  defi
		this(capacite);
		this.loadFactor = loadFactor;
	}


	public boolean estVide(){
		return taille == 0;
	}

	
	public int taille(){
		return taille;
	}

	
	public boolean contient(E element){
		return tableListes[Math.abs(element.hashCode()) % tableListes.length].contient(element);
	}

	
	public boolean ajouter(E element) {
		if (contient(element)) return false;
		tableListes[Math.abs(element.hashCode()) % tableListes.length].insererEnTete(element);
		taille++;
		return true;
	}

	public boolean enlever(E element) {
		boolean fait = tableListes[Math.abs(element.hashCode()) % tableListes.length].supprimer(element);
		if (fait){
			taille--;
			return true;
		}
		return false;
	}
	
	
	public String toString(){
	
		// Pour le debug cette methode renvoie le contenu de la structure de donnees utilisee
		// on y voit apparaitre une table avec les differentes listes, meme celles qui sont vides!
		// la methode proposee est utilisee par la classe de tests pour le défi

		// Cette methode devrait renvoyer uniquement les donnees comprises dans l'ensemble
		// Ex supplementaire 
		
		
		String aRenvoyer="";
		for (int i = 0; i < tableListes.length; i++) {
			aRenvoyer += "\ntable"+i+tableListes[i];
		}
		return aRenvoyer;
	}



}

