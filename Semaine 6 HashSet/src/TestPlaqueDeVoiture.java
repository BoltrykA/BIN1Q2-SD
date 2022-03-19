public class TestPlaqueDeVoiture {

	public final static int NBRE_LISTES = 2000;
	
	public static void main (String args[]) {
		// Attention, la methode hashCode() renvoie un entier
		// Cet entier pourrait etre negatif --> Math.abs()		
		// Cet entier doit correspondre a une liste --> %NBRE_LISTES
		String plaque = "";
		int[] tabComptage = new int[NBRE_LISTES];
		// PLAQUE = 1-AAA-000
		for (int i = 'A'; i <= 'Z'; i++) {
			for (int j = 'A'; j <= 'Z'; j++) {
				for (int k = 'A'; k < 'Z'; k++) {
					for (int l = 0; l <= 9; l++) {
						for (int m = 0; m <= 9; m++) {
							for (int n = 0; n <= 9; n++) {
								plaque = "1" + (char)i+(char)j+(char)k+l+m+n;
								Voiture voiture = new Voiture(plaque,"");
								int hash = Math.abs(voiture.hashCode())%NBRE_LISTES;
								tabComptage[hash]++;
							}
						}
					}
				}
			}
		}
		for (int elem : tabComptage){
			System.out.println(elem);
		}
	}
}