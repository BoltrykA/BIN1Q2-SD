/**
 * @author 
 *
 */

public class Consigne3Formats {
	Casier[] tousLesCasiers;
	Pile<Casier>[] tablePilesFormats;

	public Consigne3Formats(int[] formats) {
		tousLesCasiers = new Casier[formats.length];
		tablePilesFormats = new PileImpl[3];
		for (int i = 0; i < tablePilesFormats.length; i++) {
			tablePilesFormats[i] = new PileImpl<>();
		}
		for (int i = 0; i < formats.length; i++) {
			Casier c = new Casier(i);
			tousLesCasiers[formats[i]-1] = c;
			tablePilesFormats[formats[i]-1].push(c);
		}

	}

	// Retourne true s'il reste un casier libre pour le format demande, false sinon.
	public boolean resteUnCasierLibre(int format)throws IllegalArgumentException {
		if (format < 0 || format > 3) throw new IllegalArgumentException();
		return !tablePilesFormats[format-1].estVide();
	}

	/* S'il reste des casiers libres pour le format demande, un casier sera attribue, et le
	 * mot de passe de ce casier devient le parametre motDePasse.
	 * La methode renverra le numero du casier attribue.
	 * Dans le cas ou il n'y a plus de casier libre, la methode renvoie -1.
	 */
	public int attribuerCasierLibre(int format, String motDePasse) throws IllegalArgumentException{
		if (motDePasse == null || motDePasse.equals("")) throw new IllegalArgumentException();
		if (format < 0 || format > 3) throw new IllegalArgumentException();
		if (!resteUnCasierLibre(format)) return -1;
		Casier casier = tablePilesFormats[format-1].pop();
		casier.setMotDePasse(motDePasse);
		return casier.getNumero();
	}

	/* La methode libere le casier dont le numero est donne en parametre,
	 * pour autant que le numero existe et que le motDePasse soit le bon. 
	 */
	public boolean libererCasier(int numeroCasier, String motDePasse) {
		if (numeroCasier < 0 || numeroCasier > tousLesCasiers.length-1) return false;
		Casier casier = tousLesCasiers[numeroCasier];
		if (!casier.getMotDePasse().equals(motDePasse)) return false;
		casier.setMotDePasse(null);

		return false;
	}

}
