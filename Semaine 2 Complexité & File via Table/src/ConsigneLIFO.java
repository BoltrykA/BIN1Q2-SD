import java.util.ArrayList;

/**
 * @author 
 *
 */

public class ConsigneLIFO {
	private ArrayList<Casier> casiersLibres;
	private Casier[] tousLesCasiers;
	
	/**
	 * construit une consigne de gare avec tous les casiers libres au depart
	 * @param nombreCasiers le nombre de casier de la consigne
	 * @throws IllegalArgumentException si le nombre de casiers est negatif ou nul
	 */
	public ConsigneLIFO(int nombreCasiers){
		if (nombreCasiers <= 0) throw new IllegalArgumentException();
		tousLesCasiers = new Casier[nombreCasiers];
		casiersLibres = new ArrayList<>();
		for (int i = 0; i < nombreCasiers; i++) {
			Casier casier = new Casier(i);
			tousLesCasiers[i] = casier;
			casiersLibres.add(casier);
		}
	}

	/**
	 * verifie la presence d'un casier libre
	 * @return true s'il reste au moins un casier de libre, false sinon
	 */
	public boolean resteUnCasierLibre() {
		return (!casiersLibres.isEmpty());
	}

	
	/**
	 * attribue un casier libre selon le principe LIFO
	 * @param motDePasse le mot de passe qui permettra de liberer le casier
	 * @return le numero du casier attribue ou -1 s'il n'y en a plus de libre
	 * @throws IllegalArgumentException si le mot de passe est vide ou null
	 */
	public int attribuerCasierLibre(String motDePasse) {
		if (motDePasse == null || motDePasse.equals("")) throw new IllegalArgumentException();
		if (!resteUnCasierLibre()) return -1;
		Casier casierAttribue = casiersLibres.remove(casiersLibres.size()-1);
		casierAttribue.setMotDePasse(motDePasse);
		return casierAttribue.getNumero();
	}

	
	/**
	 * libere un casier
	 * @param numeroCasier le numero de casier qui doit etre libere
	 * @param motDePasse le mot de passe a comparer avec le mot de passe du casier
	 * @return true si le numero de casier existe et le mot de passe est le bon, false sinon
	 * @throws IllegalArgumentException si le mot de passe est vide ou null
	 */
	public boolean libererCasier(int numeroCasier, String motDePasse) {
		if (motDePasse == null || motDePasse.equals("")) throw new IllegalArgumentException();
		if (numeroCasier < 0 || numeroCasier >= tousLesCasiers.length || tousLesCasiers[numeroCasier].getMotDePasse() != motDePasse) return false;
		tousLesCasiers[numeroCasier].setMotDePasse(null);
		casiersLibres.add(tousLesCasiers[numeroCasier]);
		return true;
	}

}
