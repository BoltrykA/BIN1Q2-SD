import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;


public class Brocante {
	
	private int phase = 1;
	
	// suivez l'implementation imposee dans l'enonce
	private String[] tableEmplacements;
	private HashMap<String, Integer> mapRiverains;
	private ArrayDeque<Integer> pileEmplacementsLibres;
	
	
	/**
	 * initialise une brocante avec nombre emplacements
	 * @param nombreEmplacements le nombre d'emplacements
	 * @param tableRiverains la table des riverains 
	 * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
	 */
	public Brocante(int nombreEmplacements, String[] tableRiverains){
		if (nombreEmplacements <= 0 || tableRiverains == null) throw new IllegalArgumentException();
		tableEmplacements = new String[nombreEmplacements];
		mapRiverains = new HashMap<>();
		pileEmplacementsLibres = new ArrayDeque<>();

		for (String riverain : tableRiverains) {
			if (riverain != null) {
				mapRiverains.put(riverain, 0);
			}
		}
	}
	
	/**
	 * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
	 * La reservation reussit si
	 *     l'emplacement est libre
	 *     le demandeur est bien un riverain
	 *     le riverain n'a pas encore 3 emplacements
	 * @param demandeur le riverain qui demande un emplacement
	 * @param numeroEmplacement le numero de l'emplacement souhaite
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
	 * @throws IllegalStateException si on n'est pas en phase 1
	 */
	public boolean reserver(String demandeur,int numeroEmplacement){

		if (numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length) throw new IllegalArgumentException("Le numéro d'emplacement est invalide");
		if (phase != 1) throw new IllegalStateException("Pas la bonne phase !");

		if (tableEmplacements[numeroEmplacement] != null) return false;
		if (!mapRiverains.containsKey(demandeur)) return false;
		int nbrEmplacements = mapRiverains.get(demandeur);
		if (nbrEmplacements == 3) return false;

		tableEmplacements[numeroEmplacement] = demandeur;
		mapRiverains.put(demandeur, nbrEmplacements+1);

		return true;

		//Attention pour augmenter le nombre d'emplacements
		//Solution ko:
		//Integer nombreEmplacements = mapRiverains.get(demandeur);
		//mapRiverains.put(demandeur, nombreEmplacements++);
		//Solutions ok:
		//Integer nombreEmplacements = mapRiverains.get(demandeur);
		//mapRiverains.put(demandeur, ++nombreEmplacements);
		//ou:
		//Integer nombreEmplacements = mapRiverains.get(demandeur);
		//nombreEmplacements++;
		//mapRiverains.put(demandeur, nombreEmplacements);
	}
	
	/**
	 * a comme effet de passer de la phase 1 a la phase 2
	 * si deja en phase 2, rien ne doit etre fait
	 */
	public void changerPhase(){
		if (phase != 2){
			phase = 2;
			for (int i = 0; i < tableEmplacements.length; i++) {
				pileEmplacementsLibres.push(i);
			}
		}
	}

	public boolean estPlein(){
		return pileEmplacementsLibres.size() == 0;
	}
	
	/**
	 * attribue automatiquement un emmplacement libre au demandeur passe en parametre
	 * @param demandeur le demandeur d'un emplacement
	 * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
	 * @throws IllegalStateException si on n'est pas en phase 2
	 */
	public int attribuerAutomatiquementEmplacement(String demandeur){
		if (phase != 2) throw new IllegalStateException("Pas la bonne phase !");

		if (!estPlein()){
			int num = pileEmplacementsLibres.pop();
			tableEmplacements[num] = demandeur;
			if (mapRiverains.containsKey(demandeur)){
				int nbr = mapRiverains.get(demandeur);
				mapRiverains.put(demandeur, nbr+1);
			} else {
				mapRiverains.put(demandeur, 1);
			}
			return num;
		}

		return -1;
	
	}
	
	/**
	 * renvoie, sous forme d'une chaine de caracteres, tous les numeros des emplacements et leurs eventuels occupants
	 */
	public String toString(){
		// Va servir pour debugger
		String aRenvoyer = "";
		aRenvoyer = aRenvoyer + "\ntableEmplacements" + Arrays.toString(tableEmplacements);
		aRenvoyer = aRenvoyer + "\nmapRiverains" + mapRiverains.toString();
		aRenvoyer = aRenvoyer + "\npileEmplacementsLibres" + pileEmplacementsLibres.toString();
		return aRenvoyer;
		// A modifier lorsque toute l'application sera au point!
	}

}
