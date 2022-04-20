import java.util.HashMap;


public class EnsembleVoituresAutorisees {
	HashMap<String,Proprietaire> listeVoitures;

	/**
	 * construit un ensemble vide
	 */
	public EnsembleVoituresAutorisees(){
		listeVoitures = new HashMap<String, Proprietaire>();
	}

	/**
	 * ajoute une voiture a condition que celle-ci ne soit pas deja presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @param proprietaire le proprietaire de la voiture a ajouter
	 * @return true si la voiture n'etait pas encore presente, false sinon
	 */
	public boolean ajouterVoiture(String plaque, Proprietaire proprietaire){
		if (listeVoitures.containsKey(plaque)) return false;
		listeVoitures.put(plaque,proprietaire);
		return true;
	}


	/**
	 * retire une voiture a condition que celle-ci soit presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @return true si la voiture etait presente, false sinon
	 */
	public boolean retirerVoiture(String plaque){
		if (!listeVoitures.containsKey(plaque)) return false;
		listeVoitures.remove(plaque);
		return true;
	}


	
	/**
	 * verifie si la voiture est autorisee car presente dans l'ensemble
	 * @param plaque la plaque de la voiture a verifier
	 * @return true si la voiture est presente dans l'ensemble, false sinon
	 */
	public boolean voitureAutorisee(String plaque){
		return listeVoitures.containsKey(plaque);
	}
	
	/**
	 * renvoie le proprietaire de la voiture
	 * @param plaque la plaque de la voiture recherchee
	 * @return le proprietaire ou null si la plaque n'est pas dans l'ensemble
	 */
	public Proprietaire donnerProprietaire(String plaque){
		return listeVoitures.get(plaque);
	}
	
	public String toString(){
		return listeVoitures.toString();
	}
}
