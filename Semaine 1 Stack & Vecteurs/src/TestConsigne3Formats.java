
public class TestConsigne3Formats {
	
	public static void main(String[] args) {
		
		System.out.println("5 casiers ");
		System.out.println("numero 0 --> format 1");
		System.out.println("numero 1 --> format 2");
		System.out.println("numero 2 --> format 1");
		System.out.println("numero 3 --> format 3");
		System.out.println("numero 4 --> format 1");
		int[] formats = {1,2,1,3,1};
		Consigne3Formats consigne = new Consigne3Formats(formats);

		System.out.println("test 1 : casiers libres");
		System.out.println("casiers libres format 1? "+consigne.resteUnCasierLibre(1)); 
		System.out.println("casiers libres format 2? "+consigne.resteUnCasierLibre(2));
		System.out.println("casiers libres format 3? "+consigne.resteUnCasierLibre(3));
		try{
			System.out.println("casiers libres format 1? "+consigne.resteUnCasierLibre(5));
		} catch (IllegalArgumentException e){
			System.out.println("IllegalArgumentException");
		}
		System.out.println("test 2 : attributions");
		System.out.println("consigne.attribuerCasier(1,mdp1) "+consigne.attribuerCasierLibre(1,"mdp1"));
		System.out.println("consigne.attribuerCasier(1,mdp2) "+consigne.attribuerCasierLibre(1,"mdp2"));
		System.out.println("consigne.attribuerCasier(1,mdp3) "+consigne.attribuerCasierLibre(1,"mdp3"));
		System.out.println("consigne.attribuerCasier(2,mdp4) "+consigne.attribuerCasierLibre(2,"mdp3"));
		System.out.println("consigne.attribuerCasier(3,mdp5) "+consigne.attribuerCasierLibre(3,"mdp3"));
		System.out.println("casiers libres format 1? "+consigne.resteUnCasierLibre(1)); 
		System.out.println("consigne.attribuerCasier(1,mdp6)"+consigne.attribuerCasierLibre(1,"mdp6"));
		try{
			System.out.println("consigne.attribuerCasier(5,mdp7) "+consigne.attribuerCasierLibre(5,"mdp7"));
		} catch (IllegalArgumentException e){
			System.out.println("IllegalArgumentException");
		}
			
		System.out.println("test 3 : ouverture mauvais numeros");
		System.out.println("consigne.ouvrirCasier(-1, blabla) "+consigne.libererCasier(-1, "blabla"));
		System.out.println("consigne.ouvrirCasier(9, blabla) "+consigne.libererCasier(9, "blabla"));

		System.out.println("test 4 : ouverture mauvais mot de passe");
		System.out.println("Le jeu de tests doit etre complete!!!");

		//TEST4 : liberer un casier : OK

		//TEST5 : liberer le casier qui vient d'etre libere!

		// A COMPLETER


	}
}
