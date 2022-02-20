/**
 * Cette classe retient dans une liste les etudiants inscrits au bal
 * La liste contient d abord les hommes, ensuite les femmes
 * Dans chacune des sous-listes, l'ordre suit l'ordre des inscriptions
 * 
 * @author Alicia Boltryk
 *
 */
public class Bal1 {
	
	private NoeudEtudiant tete;
	private NoeudEtudiant derM;
	private NoeudEtudiant derF;
	
	/**
	 * construit un bal "vide", la liste des etudiants est vide
	 */
	public Bal1(){
		tete = null;
		derM = null;
		derF = null;
	}
	
	public String toString(){
		String aRenvoyer = "";
		NoeudEtudiant baladeur = tete;
		while(baladeur!=null){
			aRenvoyer+=" "+baladeur.etudiant.getPrenom();
			baladeur = baladeur.suivant;
		}
		return aRenvoyer;
	}
	
	/**
	 * ajoute l etudiant dans la liste en tenant compte de l'ordre prevu
	 * @param etudiant l etudiant a ajouter
	 * @throws IllegalArgumentException si l etudiant est null
	 */
	public void ajouterEtudiant(Etudiant etudiant){
		if(etudiant==null)
			throw new IllegalArgumentException("etudiant null");

		NoeudEtudiant node = new NoeudEtudiant(etudiant);
		char sex = node.etudiant.getSexe();

		if (derM == null && derF == null){ // si la chaîne est vide
			if (sex == 'F') tete = derF = node;
			else tete = derM = node;
		}
		else if (derF == null){ // si la chaîne ne contient que des hommes
			derM.suivant = node;
			if (sex == 'M') derM = node;
			else derF = node;
		}
		else if (derM == null) { // si la chaîne ne contient que des femmes
			if (sex == 'F') derF.suivant = node;
			else {
				node.suivant = tete;
				tete = derM = node;
			}
		}
		else { // s'il y a des hommes et des femmes
			if (sex == 'M'){
				node.suivant = derM.suivant;
				derM.suivant = node;
				derM = node;
			}
			else {
				derF.suivant = node;
				derF = node;
			}
		}
	}
	
	// classe interne
	private class NoeudEtudiant{
		
		private Etudiant etudiant;
		private NoeudEtudiant suivant;
		
		public NoeudEtudiant(Etudiant etudiant){
			this.etudiant = etudiant;
			this.suivant = null;
		}
		
		public NoeudEtudiant(Etudiant etudiant, NoeudEtudiant suivant){
			this.etudiant = etudiant;
			this.suivant = suivant;
		}
		
	}
}
