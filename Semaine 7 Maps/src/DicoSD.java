import java.util.HashMap;
import java.util.LinkedList;

public class DicoSD {


	HashMap<String, LinkedList<String>> dicoSD;


	// Au depart le dico est vide
	public DicoSD() {
		dicoSD = new HashMap<>();
	}

	/**
	 * ajout dans le dico une association sd-url si cette association n'est pas encore presente 
	 * @param sd une structure de donnees
	 * @param url une url vers un site internet
	 * @return true si cette association n'etait pas encore presente dans le dico, false sinon
	 */
	public boolean ajouter(String sd, String url){
		LinkedList<String> urlList = dicoSD.get(sd);
		if (urlList == null){
			urlList = new LinkedList<>();
			urlList.add(url);
			dicoSD.put(sd,urlList);
		} else {
			if (urlList.contains(url)) return false;
			urlList.add(url);
		}
		return true;
	}
	
	/**
	 * verifie si la structure de donnees se trouve dans le dico
	 * cette structure de donnees doit posseder au moins une url!
	 * @param sd
	 * @return true si sd est present, false sinon
	 */
	public boolean contient(String sd){
		return dicoSD.containsKey(sd);
	}
	
	
	/**
	 * renvoie tous les urls associes a la structure de donnees passee en parametre
	 * @param sd
	 * @return une chaine de caracteres avec les urls selon le format : [urlPile1, urlPile2] ou [] si la structure de donnees n'existe pas
	 */
	public String lesURLs(String sd){
		if (dicoSD.isEmpty() || dicoSD.get(sd) == null) return "[]";
		return dicoSD.get(sd).toString();
	}
	
	/**
	 * supprime dans le dico l'association sd-url si celle-ci est presente 
	 * @param sd une structure de donnees
	 * @param url une url vers un site internet
	 * @return true si l'association etait presente dans le dico, false sinon
	 */
	public boolean supprimer(String sd, String url){
		if (!dicoSD.containsKey(sd)) return false;
		LinkedList<String> urlList = dicoSD.get(sd);
		if (!urlList.contains(url)) return false;
		urlList.remove(url);
		if (urlList.isEmpty()) dicoSD.remove(sd);
		return true;
	}
		
}
