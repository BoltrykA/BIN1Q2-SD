public class ServeurImpressionsVar{
	// TODO


	/**
	 * construit une table avec 10 FileAttenteImpression
	 */
	public ServeurImpressionsVar() {
		//TODO
	}

	/**
	 * verifie si toutes les files sont vides
	 * @return true si toutes les files sont vides, false sinon
	 */
	public boolean estVide(){
		//TODO
		return false;
	}

	/**
	 * ajoute la demande d impression en fin de la file de priorite correspondante
	 * @param demande la demande a ajouter
	 * @throws IllegalArgumentException si la demande est null
	 */
	public void ajouter(DemandeImpressionAvecPriorite demande){
		//TODO
	}

	/**
	 * retire l'impression en tete de file de priorite la plus haute qui est non vide
	 * @return l'impression qui a ete retiree
	 * @throws FileVideException si aucune demande d impression dans la file
	 */
	public DemandeImpressionAvecPriorite retirer()throws FileVideException{
		//TODO
		return null;
	}

}