import java.util.Scanner;

public class GestionEuroMatch {

	private static Scanner scanner = new Scanner(System.in);
	private static EuroMatch euroMatch;

	public static void main(String[] args) {

		System.out.println("**************************************************************");
		System.out.println("Gestion des reservations du prochain match qui a lieu dans 24h");
		System.out.println("**************************************************************");
		System.out.println();
		System.out.print("Entrez le nombre total de places : ");
		int nombreTotalPlaces = scanner.nextInt();
		scanner.nextLine();
		euroMatch = new EuroMatch(nombreTotalPlaces);
		System.out.println();
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> afficher quelques informations sur l'etat des reservations");
			System.out.println("2 -> mettre un client dans la file d'attente");
			System.out.println("3 -> traiter le client en tete de la file d'attente");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
			case 1:
				afficherInfo();
				break;
			case 2:
				mettreEnAttente();
				break;
			case 3:
				traiterClient();
				break;
			}

		} while (euroMatch.getNombrePlacesRestantes()>0);
		System.out.println("Fin des reservations");
		System.out.println("Tous les places sont reserves");	
	}

	private static void afficherInfo() {
		// vous pouvez modifier cette methode comme vous voulez
		// cette classe ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		System.out.println(euroMatch);
	}

	private static void mettreEnAttente() {
		// vous pouvez modifier cette methode comme vous voulez
		// cette classe ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		System.out.print("Entrez le client : ");
		String client = scanner.nextLine();
		if(euroMatch.placerDansFileAttente(client)){
			System.out.println("Le client a ete mis en attente");
		}else{
			System.out.println("Le client n'a pas ete mis en attente");
		}

	}

	private static void traiterClient() {
		// vous pouvez modifier cette methode comme vous voulez
		// cette classe ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		String client = euroMatch.selectionnerClientSuivant();
		if(client==null){
			System.out.println("Il n'y a actuellement aucun client en attente");
		}else{
			System.out.println("Le client traite est "+ client);
			System.out.println("Il reste actuellement "+ euroMatch.getNombrePlacesRestantes() + " places a reserver");
			System.out.println("Entrez le nombre de places que vous desirez ( ou 0 pour arreter) : ");
			System.out.println("(Si vous avez deja fait une reservation, ce nombre sera ajoute au nombre de places que vous avez deja reservees)");
			int nombrePlacesDemandees = scanner.nextInt();
			if(nombrePlacesDemandees>0){
				try{
					// le client a deja une reservation
					// --> modifierReservation()

					if(euroMatch.modifierReservation(client, nombrePlacesDemandees)){
						System.out.println("La demande a ete acceptee");
						System.out.println("La reservation a ete modifiee");
					}else{
						System.out.println("La demande n'a pas ete acceptee");
					}
				}
				catch (IllegalStateException e){
				}
				try{

					// le client n'a pas encore de reservation
					// --> passerNouvelleReservation()	

					if(euroMatch.passerNouvelleReservation(client, nombrePlacesDemandees)){
						System.out.println("La demande a ete acceptee");
						System.out.println("Une reservation a ete creee");
					}else{
						System.out.println("La demande n'a pas ete acceptee");
					}
				}
				catch (IllegalStateException e){
				}
			}

		}

	}

}
