/**
*@author Alicia Boltryk
*
*/
public class DequeImplChainee<E> implements Deque<E> {

	private Noeud tete ;
	private Noeud queue ;
	private int taille ;
	
	public DequeImplChainee(){
		tete=null;
		queue=null;
		taille=0;
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	// tete 'a' 'b' 'c' queue : ['a','b','c']
	public DequeImplChainee(Object[] table) {
		if(table == null)
			throw new IllegalArgumentException();
		taille = 0 ;
		tete = null ;
		queue = null ;
		if(table.length==0)
			return;
		for (int i = table.length-1; i>=0;i--) {
			this.ajouterEnPremier((E) table[i]) ;
		}
	}
	
	public String toString(){
		String aRenvoyer="";
		Noeud baladeur=tete;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer+=baladeur.element;
			if (baladeur.suivant !=null)
				aRenvoyer += " " ;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}
	
	public String parcoursInverse(){
		String aRenvoyer="";
		Noeud baladeur=queue;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer+=baladeur.element;
			if (baladeur.precedent !=null)
				aRenvoyer += " " ;
			baladeur=baladeur.precedent;
		}
		return aRenvoyer;
	}
	

	public int taille() {
		return this.taille ;
	}
	

	public boolean estVide() {
		return (taille==0) ;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public void ajouterEnPremier(E element) {
		if (estVide()) {
			tete = new Noeud(element,null,null) ;
			queue = tete ;
		} else {
			tete.precedent = new Noeud(element,null,tete) ;
			tete = tete.precedent;
		}
		taille = taille + 1 ;
	}
	

	public E retirerPremier() {
		if (estVide()) throw new DequeVideException();
		E elem = tete.element;
		if (taille == 1){
			tete = queue = null;
		}
		else {
			tete = tete.suivant;
			tete.precedent = null;
		}
		taille--;
		return elem;
	}


	public void ajouterEnDernier(E element) {
		Noeud node = new Noeud(element,queue,null);
		if (estVide()) tete = queue = node;
		else {
			queue.suivant = node;
			queue = node;
		}
		taille++;
	}


	public E retirerDernier() throws DequeVideException {
		if (estVide()) throw new DequeVideException();
		E elem = queue.element;
		if (taille == 1){
			tete = queue = null;
		}
		else {
			queue = queue.precedent;
			queue.suivant = null;
		}
		taille--;
		return elem;
	}


	public E premier()throws DequeVideException {
		if (estVide()) throw new DequeVideException();
		return tete.element;
	}


	public E dernier()throws DequeVideException {
		if (estVide()) throw new DequeVideException();
		return queue.element;
	}
	
	// classe interne
	private class Noeud{
		private E element;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud(E element, Noeud precedent, Noeud suivant){
				this.element = element;
				this.suivant = suivant;
				this.precedent = precedent ;
		}
	}

}
