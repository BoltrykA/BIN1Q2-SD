import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeSDImpl<E> implements ListeSD<E>,Iterable<E> {

	private Noeud tete, queue;
	private HashMap<E, Noeud> mapElementNoeud;

	public ListeSDImpl () {
		mapElementNoeud = new HashMap<>();
		tete = new Noeud();
		queue = new Noeud();
		tete.suivant = queue;
		queue.precedent = tete;
	}

	public int taille () {
		return mapElementNoeud.size();
	}

	public boolean estVide () {
		return mapElementNoeud.isEmpty();
	}

	public boolean contient (E element) {
		return mapElementNoeud.containsKey(element);
	}

	public E premier() {
		return tete.suivant.element;
	}

	public E dernier() {
		return queue.precedent.element;
	}

	public boolean insererEnTete (E element){
		if (contient(element)) return false;
		Noeud nouvelElem = new Noeud(element);
		nouvelElem.precedent = tete;
		if (!estVide()){
			Noeud apresLui = tete.suivant;
			nouvelElem.suivant = apresLui;
			apresLui.precedent = nouvelElem;
		} else {
			nouvelElem.suivant = queue;
			queue.precedent = nouvelElem;
		}
		tete.suivant = nouvelElem;
		mapElementNoeud.put(nouvelElem.element,nouvelElem);
		return true;
	}

	public boolean insererEnQueue (E element) {
		if (contient(element)) return false;
		Noeud nouvelElem = new Noeud(element);
		nouvelElem.suivant = queue;
		nouvelElem.precedent = queue.precedent;

		Noeud avantLui = queue.precedent;
		avantLui.suivant = nouvelElem;

		queue.precedent = nouvelElem;
		mapElementNoeud.put(element, nouvelElem);
		return true;
	}

	public boolean insererApres (E element, E elementAInserer) {
		if (!contient(element) || contient(elementAInserer)) return false;
		Noeud elem = new Noeud(elementAInserer);
		Noeud elemAvant = mapElementNoeud.get(element);
		elem.precedent = elemAvant;
		elem.suivant = elemAvant.suivant;
		elemAvant.suivant.precedent = elem;
		elemAvant.suivant = elem;
		mapElementNoeud.put(elementAInserer, elem);
		return true;
	}

	public boolean insererAvant (E element, E elementAInserer) {
		if (!contient(element) || contient(elementAInserer)) return false;
		Noeud elem = new Noeud(elementAInserer);
		Noeud elemApres = mapElementNoeud.get(element);
		Noeud elemAvant = elemApres.precedent;
		elem.suivant = elemApres;
		elem.precedent = elemAvant;
		elemApres.precedent = elem;
		elemAvant.suivant = elem;
		mapElementNoeud.put(elementAInserer, elem);
		return true;
	}


	public boolean supprimer (E element) {
		if (!contient(element)) return false;
		Noeud aSupprimer = mapElementNoeud.get(element);
		Noeud avantLui = aSupprimer.precedent;
		Noeud apresLui = aSupprimer.suivant;
		avantLui.suivant = apresLui;
		apresLui.precedent = avantLui;
		mapElementNoeud.remove(element);
		return true;
	}

	public E donnerPrecedent (E element) {
		Noeud item = mapElementNoeud.get(element);
		if (item == null) return null;
		return item.precedent.element;
	}

	public E donnerSuivant (E element) {
		Noeud item = mapElementNoeud.get(element);
		if (item == null) return null;
		return item.suivant.element;
	}
	
	
	public boolean permuter (E element1, E element2) {
		// REMARQUE : CE SONT LES VALEURS QUI SONT PERMUTEES, PAS LES NOEUDS!!!
		// Il est donc inutile de revoir le chainage
		// N'oubliez pas de modifier les noeuds associes dans le map	
		if (!contient(element1) || !contient(element2)) return false;
		Noeud node1 = mapElementNoeud.get(element1);
		Noeud node2 = mapElementNoeud.get(element2);
		node1.element = element2;
		node2.element = element1;
		mapElementNoeud.replace(element1, node2);
		mapElementNoeud.replace(element2, node1);
		return true;
	}

	public Iterator<E> iterator() {
		return new IterateurImpl<E>();
	}

	public String toString () {
		String aRenvoyer = "";
		int num = 1;
		Noeud baladeur = tete.suivant;
		while (baladeur != queue) {
			aRenvoyer += num + " - " + baladeur.element + "\n";
			baladeur = baladeur.suivant;
			num++;
		}
		return aRenvoyer;   
	}

	// pour les tests
	public ListeSDImpl(E[] tableACopier) {	
		mapElementNoeud = new HashMap<E, Noeud>();
		tete = new Noeud();   // sentinelle de tete
		queue = new Noeud();  // sentinelle de queue
		Noeud prec = tete;
		for (int i = 0; i < tableACopier.length; i++) {
			Noeud nouveauNoeud = new Noeud(tableACopier[i]);
			mapElementNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		prec.suivant = queue;
		queue.precedent = prec;	
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete.suivant;
			int cpt=0;
			while (baladeur != queue) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.suivant;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// pour les tests
	public String queueTete(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = queue.precedent;
			int cpt=0;
			while (baladeur != tete) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.precedent;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// Classe interne Noeud
	private class Noeud{
		private E element;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud() {
			this(null, null, null);
		}

		private Noeud(E element) {
			this(null, element, null);
		}

		private Noeud(Noeud precedent, E element, Noeud suivant) {
			this.element = element;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

	

	// Classe interne IterateurImpl
	private class IterateurImpl<E> implements Iterator<E>{

		private Noeud noeudCourant;

		private IterateurImpl() {
			noeudCourant = tete.suivant;
		}

		public boolean hasNext() {
			return noeudCourant != queue;
		}

		// renvoie l element qui se trouve dans le noeud courant
		// le noeud courant passe au noeud suivant
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E aRenvoyer = (E)noeudCourant.element;
			noeudCourant = noeudCourant.suivant;
			return aRenvoyer;
		}

		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}
	}

}
