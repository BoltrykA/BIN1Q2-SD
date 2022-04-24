import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Alicia Boltryk
 *
 */

public class Brocante {
	
	private int phase = 1;
	
	// suivez l'implementation imposee dans l'enonce
	private Emplacement[] tableEmplacements;
	private HashMap<String, Integer> mapRiverains;
	private HashMap<String, Exposant> mapExposants;
	private HashMap<Character, ArrayDeque<Emplacement>> mapPilesEmplacement;
	private int nbrPlacesOccupees; // sert uniquement pour la methode estVide()
									// (une méthode que j'ai ajouté moi-meme pour un affichage plus propre de GestionBrocante)
	
	
	/**
	 * initialise une brocante avec nombre emplacements
	 * @param typesEmplacement la table d'emplacements avec leur type
	 * @param tableRiverains la table des riverains 
	 * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
	 */
	public Brocante(char[] typesEmplacement, String[] tableRiverains){
		if (typesEmplacement.length <= 0 || tableRiverains == null) throw new IllegalArgumentException();

		tableEmplacements = new Emplacement[typesEmplacement.length];
		mapRiverains = new HashMap<>();
		nbrPlacesOccupees = 0;

		for (int i = 0; i < tableEmplacements.length; i++) {
			tableEmplacements[i] = new Emplacement(i, typesEmplacement[i]);
		}


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
	 * @param exposant l'exposant qui demande un emplacement
	 * @param numeroEmplacement le numero de l'emplacement souhaite
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
	 * @throws IllegalStateException si on n'est pas en phase 1
	 */
	public boolean reserver(Exposant exposant,int numeroEmplacement){

		if (numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length) throw new IllegalArgumentException("Le numéro d'emplacement est invalide");
		if (phase != 1) throw new IllegalStateException("Pas la bonne phase !");

		// les tests de validité sont également faits dans GestionBrocante, mais je les garde quand même dans cette fonction
		// (en imaginant que ma fonction pourrait être utilisée dans un autre contexte).

		if (!estLibre(numeroEmplacement)) return false;
		String demandeur = exposant.getNom();
		if (!estUnRiverain(demandeur)) return false;
		int nbrEmplacements = nombreEmplacementsRiverain(demandeur);
		if (nbrEmplacements == 3) return false;

		tableEmplacements[numeroEmplacement].setExposant(exposant);
		mapRiverains.put(demandeur, ++nbrEmplacements);
		nbrPlacesOccupees++;

		return true;
	}

	/**
	 * vérifie si le nom passé en parametre fait partie du map
	 * @param nom le nom de l'exposant
	 * @return true si c'est un riverain, false sinon
	 */
	public boolean estUnRiverain(String nom){
		return mapRiverains.containsKey(nom);
	};
	
	/**
	 * a comme effet de passer de la phase 1 a la phase 2
	 * si deja en phase 2, rien ne doit etre fait
	 */
	public void changerPhase(){
		if (phase != 2){
			phase = 2;
			mapExposants = new HashMap<>();
			mapPilesEmplacement = new HashMap<>();
			for (Emplacement emplacement : tableEmplacements) {
				Exposant currentExposant = emplacement.getExposant();
				char typeEmplacement = emplacement.getType();

				if (!mapPilesEmplacement.containsKey(typeEmplacement)){
					mapPilesEmplacement.put(typeEmplacement, new ArrayDeque<>());
				}

				ArrayDeque<Emplacement> pileEmplacements = mapPilesEmplacement.get(typeEmplacement);
				if (currentExposant == null) pileEmplacements.push(emplacement);
				else mapExposants.put(currentExposant.getNom(), currentExposant); // les riverains deviennent exposants et je les place dans le mapExposants
			}
		}
	}
	
	/**
	 * attribue automatiquement un emmplacement libre au demandeur passe en parametre
	 * @param exposant le demandeur d'un emplacement
	 * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
	 * @throws IllegalStateException si on n'est pas en phase 2
	 */
	public int attribuerAutomatiquementEmplacement(Exposant exposant, char type){
		if (phase != 2) throw new IllegalStateException("Pas la bonne phase !");

		//idem ici. je garde les tests de validité des paramètres, même si je les réitère dans GestionBrocante.
		// je les garde en imaginant que la fonction pourrait être utilisée autre part que dans GestionBrocante.

		if (emplacementLibre(type)){
			ArrayDeque<Emplacement> emplacementsDuType = mapPilesEmplacement.get(type);
			if (emplacementsDuType.isEmpty()) return -1;
			Emplacement emplacement = emplacementsDuType.pop();
			emplacement.setExposant(exposant);

			String nom = exposant.getNom();

			if (!estUnExposant(nom)){
				mapExposants.put(nom, exposant);
			}

			exposant.ajouterEmplacement(emplacement);
			nbrPlacesOccupees++;
			return emplacement.getNumero();
		}

		return -1;
	
	}

	public boolean libererEmplacement(String nom, int numeroEmplacement){
		if (phase != 2) throw new IllegalStateException("Pas la bonne phase !");
		if (numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length)
			throw new IllegalArgumentException("Le numéro d'emplacement est invalide");

		//idem ici. je garde les tests de validité des paramètres, même si je les réitère dans GestionBrocante.
		// je les garde en imaginant que la fonction pourrait être utilisée autre part que dans GestionBrocante.

		if (!estUnExposant(nom)) return false;

		Exposant exposant = getExposant(nom);
		Emplacement emplacement = tableEmplacements[numeroEmplacement];
		if (!exposant.supprimerEmplacement(emplacement)) return false;

		char type = emplacement.getType();
		mapPilesEmplacement.get(type).push(emplacement);

		System.out.println(emplacement.getExposant());
		emplacement.setExposant(null);
		nbrPlacesOccupees--;

		return true;
	}

	/**
	 * indique si le nom fait partie des exposants
	 * @param nom
	 * @return true si il fait partie du map, false sinon
	 */
	public boolean estUnExposant(String nom){
		return mapExposants.containsKey(nom);
	};

	/**
	 * renvoie le nombre d'emplacements possédé par le riverain
	 * @param nom le nom du riverain
	 * @return le nombre d'exposants qu'il possede
	 */
	public int nombreEmplacementsRiverain(String nom){
		return mapRiverains.get(nom);
	}

	/**
	 * indique si l'emplacement est libre
	 * @param numeroEmplacement
	 * @return true si il est libre, false sinon
	 */
	public boolean estLibre(int numeroEmplacement){
		if (numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length) return false;
		return tableEmplacements[numeroEmplacement].getExposant() == null;
	};

	/**
	 * indique si il y a encore un emplacement libre
	 * @return true si emplacement encore libre, false sinon
	 */
	public boolean emplacementLibre(char type){
		if (!mapPilesEmplacement.containsKey(type)) return false;
		return !mapPilesEmplacement.get(type).isEmpty();
	}

	/**
	 *
	 * @param numEmplacement
	 * @return l'emplacement associé
	 */
	public Emplacement getEmplacement(int numEmplacement){
		if (numEmplacement < 0 || numEmplacement >= tableEmplacements.length)
			throw new IllegalArgumentException("Le numéro d'emplacement est invalide");
		return tableEmplacements[numEmplacement];
	}



	/**
	 *
	 * @param nom de l'exposant (String)
	 * @return l'exposant associé au nom dans le mapExposants
	 */
	public Exposant getExposant(String nom){
		return mapExposants.get(nom);
	}

	/**
	 *
	 * @return l'ensemble des exposants dans le mapExposants
	 */
	public Iterator<Exposant> tousLesExposants(){
		return mapExposants.values().iterator();
	}

	/**
	 *
	 * @return true si la brocante n'est occupée par aucun exposant, false sinon
	 */
	public boolean estVide(){
		return nbrPlacesOccupees == 0;
	}

	public boolean estPleine(){
		return mapPilesEmplacement.values().isEmpty();
	}
	
	/**
	 * renvoie, sous forme d'une chaine de caracteres, tous les numeros des emplacements et leurs eventuels occupants
	 */
	public String toString(){
		// Va servir pour debugger
		String aRenvoyer = "";
		aRenvoyer = aRenvoyer + "\ntableEmplacements" + Arrays.toString(tableEmplacements);
		if (phase == 1)
			aRenvoyer = aRenvoyer + "\nmapRiverains" + mapRiverains.toString();
		else {
			aRenvoyer = aRenvoyer + "\nmapPileEmplacementsLibres" + mapPilesEmplacement;
			aRenvoyer = aRenvoyer + "\nmapExposants" + mapExposants.toString();
		}
		return aRenvoyer;
		// A modifier lorsque toute l'application sera au point!
	}

}
