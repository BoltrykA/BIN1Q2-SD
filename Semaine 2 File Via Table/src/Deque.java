import java.util.Iterator;

/**
 * @author Alicia BOLTRYK
 */
public interface Deque<E> {

    /**
     * verifie si la deque est vide
     * @return true si vide, false sinon
     */
    public boolean estVide();

    /**
     * renvoie l'element qui se trouve en tete de deque sans l'enlever de la file
     * @return l'element en tete
     * @throws FileVideException si la file est vide
     */
    public E premier() throws FileVideException;

    /**
     * renvoie l'element qui se trouve en fin de deque (queue) sans l'enlever de la file
     * @return l'element en fin de deque
     * @throws FileVideException si la file est vide
     */
    public E dernier() throws FileVideException;

    /**
     * renvoie le nombre d'elements qui se trouvent dans la deque
     * @return nombre d'elements
     */
    public int taille();

    /**
     * agrandit la deque (double de sa longueur actuelle)
     *
     */
    public void agrandirTable();

    /**
     * ajoute un element en debut de deque
     * @param element l'element a ajouter
     */
    public void ajouterEnPremier(E element);

    /**
     * ajoute un element en fin de deque
     * @param element l'element a ajouter
     */
    public void ajouterEnDernier(E element);

    /**
     * renvoie l'element qui se trouve en tete de deque et l'enleve de la file
     * @return l'element en tete
     * @throws FileVideException si la file est vide
     */
    public E retirerEnPremier() throws FileVideException;

    /**
     * renvoie l'element qui se trouve en fin de deque et l'enleve de la file
     * @return l'element en fin
     * @throws FileVideException si la file est vide
     */
    public E retirerEnDernier() throws FileVideException;


}
