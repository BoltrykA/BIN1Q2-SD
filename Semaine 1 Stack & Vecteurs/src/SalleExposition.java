/**
 * @author Alicia BOLTRYK
 *
 */
public class SalleExposition {

	private Vecteur<Emplacement> emplacements; // tous les emplacements d'oeuvres
	private Pile<Emplacement> emplacementsLiberes; // une pile des emplacements vides
	
	/**
	 * construit une salle d'exposition vide (aucun emplacement)
	 */
	public SalleExposition(){
		emplacements = new VecteurImpl<Emplacement>();
		emplacementsLiberes = new PileImpl<Emplacement>();
	}
	
	/**
	 * renvoie le nombre d'emplacements 
	 * @return le nombre d'emplacements crees (libres ou occupes)
	 */
	public int nombreEmplacements(){
		return emplacements.taille();
	}

	/**
	 * renvoie le nombre d'oeuvres 
	 * @return le nombre d'oeuvres 
	 */
	public int nombreOeuvres(){
		// nombre d'oeuvres = nombre d'emplacements occupés
		return nombreEmplacements() - emplacementsLiberes.taille();
	}

	/**
	 * ajoute une oeuvre dans la salle d'exposition
	 * (ajoute un emplacement si necessaire)
	 * precondition : l'oeuvre n'y est pas encore
	 * Votre methode ne doit pas faire cette verification!
	 * @param oeuvre l'oeuvre a ajouter
	 * @return le numero de l'oeuvre ajoutee
	 * @throws IllegalArgumentException si l'oeuvre est vide ou null
	 */
	public int ajouter(String oeuvre){
		if (oeuvre == null || oeuvre.equals("")) throw new IllegalArgumentException();
		int numeroOeuvre = nombreOeuvres();
		if (!emplacementsLiberes.estVide()){ // si emplacementsLiberes est pas vide je pop et j'utilise son rang pr numeroOeuvre.
			numeroOeuvre = emplacementsLiberes.pop().getNumero();
			emplacements.remplace(numeroOeuvre, new Emplacement(numeroOeuvre,oeuvre));
		} else {
			emplacements.ajoute(new Emplacement(numeroOeuvre,oeuvre));
		}
		return numeroOeuvre;
	}

	
	/**
	 * renvoie l'oeuvre correspondante au numero passe en parametre
	 * @param numeroOeuvre le numero de l'oeuvre recherchee
	 * @return l'oeuvre ou null si le numeroOeuvre ne correspond a aucune oeuvre actuellement exposee 
	 */
	public String consulter(int numeroOeuvre){
		return emplacements.element(numeroOeuvre).getOeuvre();
	}


	/**
	 * supprime l'oeuvre correspondante au numero passe en parametre
	 * @param numeroOeuvre le numero de l'oeuvre a supprimer
	 * @return l'oeuvre supprimee ou null si le numeroOeuvre ne correspond a aucune oeuvre actuellement exposee 
	 */
	public String supprimer(int numeroOeuvre){
		//dans le vecteur, l'indice supprimé ne doit pas disparaitre. il doit juste être remplacé par null.
		// je veux push la pile emplacementsLiberes et retirer du vecteur l'oeuvre
		Emplacement elemASuppr = emplacements.element(numeroOeuvre);
		if (elemASuppr != null){
			emplacements.remplace(numeroOeuvre,null);
			emplacementsLiberes.push(elemASuppr);
			return elemASuppr.getOeuvre();
		}
		return null;
		
	}
	
	/**
	 * renvoie un String avec les oeuvres existantes (pas les emplacements!)
	 * Les numeros et les oeuvres doivent apparaitre 
	 */
	public String donnerCatalogue(){
		String infos = "Oeuvres existantes :\n";
		for (int i = 0; i < nombreEmplacements(); i++) {
			if (emplacements.element(i) != null){
				infos += "Numéro " + emplacements.element(i).getNumero() + " : '" + emplacements.element(i).getOeuvre() + "'\n";
			}
		}
		if (nombreEmplacements() == 0) infos += "Aucune oeuvre";
		return infos;
	}
}
