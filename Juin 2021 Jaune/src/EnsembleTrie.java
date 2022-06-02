public interface EnsembleTrie<E>{
	
	/**
	 * verifie si l'ensemble est vide
	 * @return true si l'ensemble est vide, false sinon
	 */
	public boolean estVide();

	
	/**
	 * renvoie le cardinal de l ensemble
	 * @return le nombre d'elements presents
	 */
	public int taille();
	

	
	/**
	 * renvoie le plus grand element de l ensemble
	 * @return le plus grand element ou null si l'ensemble est vide
	 */
	public E elementLePlusGrand();


	/**
	 * ajoute element si element n appartient pas a l ensemble
	 * @param element l'element a ajouter
	 * @return true si l'element a ete ajoute, false sinon
	 */
	public boolean ajoute(E element);

	
	/**
	 * renvoie le plus petit element qui est strictement plus grand que l'element passe en parametre
	 * @param element l'element dont on recherche le suivant
	 * @return l'element suivant ou null si un tel element n'existe pas
	 */
	public E elementSuivant(E element);

	
	
}
