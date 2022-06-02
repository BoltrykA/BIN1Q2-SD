import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Boltryk Alicia

public class DequeImpl<E> implements Deque<E>{

	private Object[] table;
	private int indicePremier; 
	private int indiceDernier;
	private int taille;
	private int numVersion = 0; // pour l'iterateur

	public DequeImpl(){
		table = new Object[5];
		indicePremier = 0;
		indiceDernier = 0;
		taille = 0;
	}

	// Va servir pour les tests 
	// A ne pas modifier!!!
	public DequeImpl(Object[] tableARecopier){
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		table = new Object[tableARecopier.length+4];
		for (int i = 0; i < tableARecopier.length; i++) {
			table[i+2]=tableARecopier[i];
		}
		indicePremier = 2;
		indiceDernier = indicePremier+tableARecopier.length-1;
		taille = tableARecopier.length;
	}

	// Va servir pour les tests 
	// A ne pas modifier!!!
	public String toString(){
		String aRenvoyer = "";
		for (int i = indicePremier; i <= indiceDernier; i++) {
			aRenvoyer += " "+table[i];
		}
		return aRenvoyer;
	}

	@Override
	public boolean estVide() {
		return taille==0;
	}

	@Override
	public int taille() {
		return taille;
	}

	private void agrandirTable() {
		Object[] newTable = new Object[table.length*2];
		int exIndicePremier = indicePremier;
		indicePremier = (table.length / 2);
		indiceDernier = indicePremier + (taille-1);
		for (int i = indicePremier; i <= indiceDernier; i++) {
			newTable[i] = table[exIndicePremier++];
		}
		table = newTable;
	}

	@Override
	public void ajouterEnPremier(E element) {
		if (element == null) throw new IllegalArgumentException();
		if (indicePremier == 0) agrandirTable();
		table[--indicePremier] = element;
		taille++;
		if (taille == 1) indicePremier = indiceDernier;
		numVersion++;
	}


	@Override
	public void ajouterEnDernier(E element) {
		if (element == null) throw new IllegalArgumentException();
		if (indiceDernier == table.length-1) agrandirTable();
		table[++indiceDernier] = element;
		taille++;
		if (taille == 1) indicePremier = indiceDernier;
		numVersion++;
	}

	@Override
	public E premier() {
		return (E) table[indicePremier];

	}

	@Override
	public E dernier(){
		return (E) table[indiceDernier];
	}

	@Override
	public E supprimerPremier(){
		if (estVide()) return null;
		taille--;
		numVersion++;
		return (E) table[indicePremier++];
	}

	@Override
	public E supprimerDernier() {
		if (estVide()) return null;
		taille--;
		numVersion++;
		return (E) table[indiceDernier--];
	}

	@Override
	public Iterator<E> iterator() {
		return new IterateurImpl<E>();
	}

	private class IterateurImpl<E> implements Iterator<E>{

		private int indiceCourant;
		private int version;

		private IterateurImpl() {
			indiceCourant = indicePremier;
			version = numVersion;
		}

		@Override
		public boolean hasNext() {
			if (estVide()) return false;
			return ((indiceCourant) <= indiceDernier);
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			version++;
			return (E) table[indiceCourant++];
		}

		@Override
		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}

	}
}
