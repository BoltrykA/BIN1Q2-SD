
public class JoueurTennis {

	private String nom;
	private  char sexe; // 'H' --> homme, 'F' --> femme
	private String nationalite; // initiales du pays par exemples B, F, CH;

	
	
	public JoueurTennis(String nom, char sexe, String nationalite) {
		if(nom==null||nom.length()==0)
			throw new IllegalArgumentException();
		if(sexe!='H' && sexe!='F')
			throw new IllegalArgumentException();
		this.nom = nom;
		this.sexe = sexe;
		this.nationalite = nationalite;
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
	
	
	
	@Override
	public String toString() {
		return "Danseur [nom=" + nom + ", sexe=" + sexe + ", nationalite="
				+ nationalite + "]";
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
		JoueurTennis other = (JoueurTennis) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	
}
