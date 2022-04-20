import java.util.LinkedList;

public class Societe {

	private int numeroSociete;
	private String nom;
	private LinkedList<Integer> lesHangars;
	
	/**
	 * construit une societe sans hangar
	 * @param numeroSociete son numero
	 * @param nom son nom
	 * @throws IllegalArgumentException si le numero de la societe est negatif ou nul 
	 *                                  si le nom est null ou ""
	 */
	public Societe(int numeroSociete, String nom) {
		if(numeroSociete<=0)
			throw new IllegalArgumentException();
		if(nom == null || nom.equals(""))
			throw new IllegalArgumentException();
		this.numeroSociete = numeroSociete;
		this.nom = nom;
		lesHangars = new LinkedList<Integer>();
	}
	
	
	/**
	 * renvoie un String avec les hangars occupes par la societe 
	 * @return 
	 */
	public String lesHangars() {
		return lesHangars.toString();
		// A NE PAS MODIFIER --> VA SERVIR POUR LES TESTS
	}

	/**
	 * ajoute un hangar si celui-ci n'y est pas encore
	 * @param numeroHangar le numero du hangar ajoute
	 * @return
	 */
	public boolean ajouterHangar(int numeroHangar){
		if (lesHangars.contains(numeroHangar)) return false;
		lesHangars.add(numeroHangar);
		return true;
	
	}

	/**
	 * supprime le numéro du hangar de la liste chainée
	 * @param numeroHangar
	 * @return true si la suppression a pu etre faite, false sinon
	 */
	public boolean supprimerHangar (int numeroHangar){
		Hangar hangar = new Hangar(numeroHangar);
		if (!lesHangars.contains(numeroHangar)) return false;
		int i = lesHangars.indexOf(numeroHangar);
		lesHangars.remove(i);
		return true;
	}

	public int getNumeroSociete() {
		return numeroSociete;
	}
	
	public String getNom() {
		return nom;
	}
	

	@Override
	public String toString() {
		return "Societe [numeroSociete=" + numeroSociete + ", nom=" + nom
				+ ", lesHangars=" + lesHangars + "]";
	}


	@Override
	public int hashCode() {
		return numeroSociete;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Societe other = (Societe) obj;
		if (numeroSociete != other.numeroSociete)
			return false;
		return true;
	}
	
	
	
	
	

	
	
	
	
	
}
