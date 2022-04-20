import java.util.HashMap;
import java.util.LinkedList;


/**
 * 
 * @author Alicia Boltryk
 * 
 */

public class Entrepot {
	private Hangar[] tableHangars;
	private HashMap<Integer, Societe> dicoInfosSociete;
	private int nombreHangarsOccupes;

	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if (nombreHangars <= 0) throw new IllegalArgumentException();
		tableHangars = new Hangar[nombreHangars];
		dicoInfosSociete = new HashMap<>();
		nombreHangarsOccupes = 0;
	}
	
	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire : 
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete
	 * @param nomSociete
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		if (nombreHangarsOccupes == tableHangars.length) return -1;
		if (numeroSociete < 0) return -1;

		int numeroHangar = numeroSociete % tableHangars.length;
		while (tableHangars[numeroHangar] != null){
			numeroHangar = (numeroHangar + 1) % tableHangars.length;

		}
		Hangar nouveauHangar = new Hangar(numeroHangar);
		Societe societe;



		if (dicoInfosSociete.containsKey(numeroSociete)) {
			societe = dicoInfosSociete.get(numeroSociete);
		} else {
			societe = new Societe(numeroSociete,nomSociete);
			dicoInfosSociete.put(numeroSociete, societe);
		}
		nouveauHangar.setSociete(societe);
		societe.ajouterHangar(nouveauHangar.getNumeroHangar());
		tableHangars[numeroHangar] = nouveauHangar;
		nombreHangarsOccupes++;

		return nouveauHangar.getNumeroHangar();

	}

	/**
	 * renvoie le nom de la société si la société existe dans le dico
	 * renvoie null si la société n'existe pas
	 * @param numeroSociete
	 * @return le nom de la société
	 */
	public String getNomSociete(int numeroSociete){
		if (!dicoInfosSociete.containsKey(numeroSociete)) return null;
		return dicoInfosSociete.get(numeroSociete).getNom();
	}

	
	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		if (!dicoInfosSociete.containsKey(numeroSociete)) return null;
		return dicoInfosSociete.get(numeroSociete);
	}

	/**
	 * Méthode renvoyant la liste des numéros de hangars libres.
	 * @return la liste des numéros de hangars libres
	 */
	public LinkedList<Integer> getNumeroHangarsLibres() {
		LinkedList<Integer> liste = new LinkedList<>();
		for (int i = 0; i < tableHangars.length; i++) {
			if (tableHangars[i] == null) liste.add(i);
		}
		return liste;
	}

	public boolean estPlein(){
		return nombreHangarsOccupes == tableHangars.length;
	}

	public boolean estVide(){
		return nombreHangarsOccupes == 0;
	}

	/**
	 * libère le hangar au numéro passé en paramètre.
	 * @param numeroHangar
	 * @return true si la libération a pu s'effectuer, false sinon (si le hangar est déjà libre)
	 */
	public boolean libererHangar(int numeroHangar){
		if (tableHangars[numeroHangar] == null) return false;
		Societe societeDuHangar = tableHangars[numeroHangar].getSociete();
		societeDuHangar.supprimerHangar(numeroHangar);
		tableHangars[numeroHangar] = null;
		nombreHangarsOccupes--;
		return true;
	}
}
