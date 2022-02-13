import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Alicia BOLTRYK
 */

public class DequeImpl<E> implements Deque<E>{

    private Object[] tableDeque;
    private int indicePremier;
    private int indiceDernier;
    private int taille;

    public DequeImpl(){
        tableDeque = new Object[5];
        indicePremier = indiceDernier = taille = 0;
    }

    public boolean estVide() {
        return taille == 0;
    }

    public int taille() {
        return taille;
    }

    public E premier() throws FileVideException {
        if (estVide()) throw new FileVideException();
        return (E) tableDeque[indicePremier];
    }

    public E dernier() throws FileVideException {
        if (estVide()) throw new FileVideException();
        return (E) tableDeque[indiceDernier];
    }

    public void ajouterEnPremier(E element) {
        if (tableDeque[0] != null) agrandirTable();
        if (estVide()) indicePremier = indiceDernier = tableDeque.length/2;
        else indicePremier--;
        tableDeque[indicePremier] = element;
        taille++;
    }

    public void ajouterEnDernier(E element) {
        if (!estVide() && tableDeque[taille-1] != null) agrandirTable();
        if (estVide()) indicePremier = indiceDernier = tableDeque.length/2;
        else indiceDernier = indicePremier + taille;
        tableDeque[indiceDernier] = element;
        taille++;
    }

    public void agrandirTable() {

        Object[] tTemp = new Object[tableDeque.length+5];
        int indicePremierTemp, indiceDernierTemp;
        indicePremierTemp = indiceDernierTemp = 3;
        for (int i = indicePremier; i <= indiceDernier; i++) {
            tTemp[indiceDernierTemp++] = tableDeque[i];
        }
        tableDeque = tTemp;
        indicePremier = indicePremierTemp;
        indiceDernier = indiceDernierTemp;
        }

    public E retirerEnPremier() throws FileVideException {
        if(estVide()) throw new FileVideException();
        E res = (E) tableDeque[indicePremier++];
        taille--;
        return res;
    }

    public E retirerEnDernier() throws FileVideException {
        if(estVide()) throw new FileVideException();
        E res = (E) tableDeque[indiceDernier--];
        taille--;
        return res;
    }

    @Override
    public String toString() {
        String rep = "";
        for (int i = indicePremier; i <= indiceDernier; i++) {
            rep += " " + tableDeque[i];
        }
        return rep;
    }

}
