
public class Danseur {

	private String nom;
	private  char sexe; // 'H' --> homme, 'F' --> femme
	private String nationalite; // initiales du pays par exemples B, GB, CH;
	private int experience; // nombre d'annees d'experience
	
	
	public Danseur(String nom, char sexe, String nationalite, int experience) {
		if(nom==null||nom.length()==0)
			throw new IllegalArgumentException();
		if(sexe!='H' && sexe!='F')
			throw new IllegalArgumentException();
		if(experience<0)
			throw new IllegalArgumentException();
		this.nom = nom;
		this.sexe = sexe;
		this.nationalite = nationalite;
		this.experience = experience;
	}
	
	public String getNom() {
		return nom;
	}
	
	public char getSexe() {
		return sexe;
	}
	
	public String getNationalite() {
		return nationalite;
	}
	
	public int getExperience() {
		return experience;
	}
	
	@Override
	public String toString() {
		return "Danseur [nom=" + nom + ", sexe=" + sexe + ", nationalite="
				+ nationalite + ", experience=" + experience + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Danseur other = (Danseur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	
}
