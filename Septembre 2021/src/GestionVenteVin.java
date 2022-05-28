import java.util.Scanner;

public class GestionVenteVin {
	
	private static Scanner scanner = new Scanner(System.in);
	private static VenteVin session;
	
	public static void main(String[] args) {
	
		System.out.println("************");
		System.out.println("Vente de vin");
		System.out.println("************");
	    System.out.println();
		System.out.print("Entrez le nombre de bouteilles a vendre : ");
		int nombreBouteillesMisEnVente= scanner.nextInt();
		scanner.nextLine();
		session = new VenteVin(nombreBouteillesMisEnVente);
		int choix = 0;
		do {
			System.out.println();
			System.out.println("    1   -> afficher quelques informations sur l'etat des ventes");
			System.out.println("    2   -> mettre un client en attente");
			System.out.println("    3   -> traiter le client le plus prioritaire");
			System.out.println("  autre -> arreter les ventes");
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
		
		} while (choix>=1&&choix<=3);
		
		System.out.println("Fin des ventes");
	}

	private static void afficherInfo() {
		// vous pouvez modifier cette methode comme vous voulez
		// cette classe ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		System.out.println(session);	
	}

	private static void mettreEnAttente() {
		// vous pouvez modifier cette methode comme vous voulez
		// cette classe ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		System.out.print("Entrez le client : ");
		String client = scanner.nextLine();
		if(session.mettreEnAttente(client)){
			System.out.println("Le client a ete mis en attente");
		}else{
			System.out.println("Le client n'a pas ete mis en attente");
		}

	}

	private static void traiterClient() {
		// vous pouvez modifier cette methode comme vous voulez
		// cette classe ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		String client = session.selectionnerClientSuivant();
		if(client==null){
			System.out.println("Il n'y a actuellement aucun client en attente");
		}else{
			System.out.println("Le client traite est "+ client);
			System.out.println("Il reste actuellement "+ session.getNombreBouteillesRestantes() + " bouteilles en vente");
			System.out.println();
			System.out.println("    1    -> demander un bouteille (de plus)");
			System.out.println("    2    -> supprimer sa commande");
			System.out.println("   autre -> revenir au menu principal");
			System.out.println();
			System.out.print("Votre choix : ");
			int choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
			case 1:
				ajouter(client);
				break;
			case 2:
				supprimer(client);
				break;
			}
	}

}

	private static void ajouter(String client) {
		
		if(session.demanderUneBouteille(client)){
			System.out.println("La demande a abouti");
		}else{
			System.out.println("La demande n'a pas abouti");
		}
		
	}

	private static void supprimer(String client) {
		if(session.supprimerCommande(client)){
			System.out.println("La commande a ete supprimee");
		}else{
			System.out.println("Aucune commande n'a ete supprimee");
		}
		
	}

}