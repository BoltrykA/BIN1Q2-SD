/**
 *
 * @author Alicia Boltryk
 */

public class ServeurImpressions{

	FileAttenteImpressions[] tableFiles;
	
	/**
	 * construit une table avec 10 files FileAttenteImpressions
	 */
	public ServeurImpressions() {
		tableFiles = new FileAttenteImpressions[10];
		for (int i = 0; i < tableFiles.length; i++) {
			tableFiles[i] = new FileAttenteImpressions();
		}
	}
	
	/**
	 * verifie si toutes les files sont vides
	 * @return true si toutes les files sont vides, false sinon
	 */
	public boolean estVide(){
		for (int i = 0; i < tableFiles.length; i++) {
			if (!tableFiles[i].estVide()) return false;
			}
		return true;
		}
	
	/**
	 * ajoute la demande d impression en fin de la file de priorite correspondante
	 * @param demande la demande a ajouter
	 * @throws IllegalArgumentException si la demande est null
	 */
	public void ajouter(DemandeImpressionAvecPriorite demande){
		if (demande == null) throw new IllegalArgumentException();
		tableFiles[demande.getPriorite()].ajouter(demande);
	}
	
	/**
	 * retire l'impression en tete de file de priorite la plus haute qui est non vide
	 * @return l'impression qui a ete retiree
	 * @throws FileVideException si aucune demande d impression dans la file
	 */
	public DemandeImpressionAvecPriorite retirer()throws FileVideException{
		if (estVide()) throw new FileVideException();
		DemandeImpressionAvecPriorite demande = null;
		for (int i = tableFiles.length - 1; i >= 0; i--) {
			if (!tableFiles[i].estVide()){
				DemandeImpression demandeImp = tableFiles[i].retirer();
				demande = new DemandeImpressionAvecPriorite(demandeImp.getCheminAcces(),demandeImp.getNomDocument(), demandeImp.getNomUtilisateur(), i);
				return demande;
			}
		}
		return demande;
	}
	
}


