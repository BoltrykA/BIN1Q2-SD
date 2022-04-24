import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Scanner;


public class GestionBrocante {
	
	private static Scanner scanner = new Scanner(System.in);
	private static Brocante brocante;

	public static void main(String[] args) {	
		
		System.out.println("**********************");
		System.out.println("gestion d'une brocante");
		System.out.println("**********************");
	    System.out.println();
		System.out.println("configuration de la brocante");
		System.out.println("----------------------------");
		System.out.print("Entrez le nombre d'emplacements : ");
		int nombreEmplacements = scanner.nextInt();
		char[] tableEmplacements = new char[nombreEmplacements];
		for (int i = 0; i < tableEmplacements.length; i++) {
			System.out.println("Entrez le type de l'emplacement numéro " + i + ": ");
			tableEmplacements[i] = scanner.next().charAt(0);
			System.out.println();
		}
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain "+ (i+1) + ": ");
			tableRiverains[i] = scanner.nextLine();
		}

		brocante = new Brocante(tableEmplacements, tableRiverains);
		System.out.println();
		
		
		System.out.println("Phase 1 -- Riverains uniquement et 3 emplacements par riverain max");
		System.out.println("-------");
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
			case 1:
				reserverPhase1();
				break;
			case 2:
				afficherTout();
				break;
			}

		} while (choix >= 1 && choix <= 2);
		
		brocante.changerPhase();
		System.out.println();
		System.out.println();
		
		System.out.println("Phase 2 -- N'importe quel exposant et autant d'emplacements que souhaité");
		System.out.println("-------");
		choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> Consulter un exposant via son nom");
			System.out.println("4 -> Lister tous les exposants");
			System.out.println("5 -> Libérer un emplacement");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
			case 1:
				reserverPhase2();
				break;
			case 2:
				afficherTout();
				break;
			case 3:
				consulterExposant();
				break;
			case 4:
				tousLesExposants();
				break;
			case 5:
				libererEmplacement();
				break;
			}

		} while (choix >= 1 && choix <= 5);
		
		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		if (!brocante.estLibre(numero)){
			System.out.println("Désolé, cet emplacement n'est pas disponible ou inexistant.");
			return;
		}
		scanner.nextLine();
		System.out.print("Entrez le nom du demandeur: ");
		String nom = scanner.nextLine();
		if (!brocante.estUnRiverain(nom)){
			System.out.println("Désolé, l'exposant n'est pas un riverain.");
			return;
		}
		if (brocante.nombreEmplacementsRiverain(nom) == 3){
			System.out.println("Ce riverain a déjà trois emplacements de réservés !");
			return;
		}

		Exposant exposant = brocante.getExposant(nom);

		if (exposant == null){
			System.out.println("Le riverain n'est pas encore repertorié parmi les exposants.");
			System.out.println("Entrez son adresse e-mail: ");
			String email = scanner.nextLine();
			System.out.println("Entrez son numéro de téléphone: ");
			String numTel = scanner.nextLine();
			exposant = new Exposant(nom,email,numTel);
		}


		boolean aReussi = false;
		try {
			aReussi = brocante.reserver(exposant, numero);
		} catch (IllegalArgumentException | IllegalStateException e){
			System.out.println(e.getMessage());
		}

		if (aReussi) System.out.println("L'attribution a réussi.");
		else System.out.println("L'attribution n'a pas abouti.");
		
	}

	private static void reserverPhase2() {

		System.out.println("Entrez le type d'emplacement souhaité : ");
		char type = scanner.next().charAt(0);
		scanner.nextLine();

		if (!brocante.emplacementLibre(type)){
			System.out.println("Désolé, il n'y a pas d'emplacement de ce type disponible !");
			return;
		}
		System.out.print("Entrez le nom du demandeur: ");

		String nom = scanner.nextLine();
		Exposant exposant;

		if (!brocante.estUnExposant(nom)){
			System.out.println("Cette personne n'a pas encore d'emplacement dans la brocante.");
			System.out.println("Entrez son adresse e-mail: ");
			String email = scanner.nextLine();
			System.out.println("Entrez son numéro de téléphone: ");
			String numTel = scanner.nextLine();
			exposant = new Exposant(nom,email,numTel);

		} else {
			System.out.println("Cette personne est déjà enregistrée dans la liste des exposants.");
			exposant = brocante.getExposant(nom);
		}


		int numero = brocante.attribuerAutomatiquementEmplacement(exposant, type);

		if (numero != -1) System.out.println(nom + " s'est fait attribuer l'emplacement numéro " + numero + " et de type " + type + ".");
		else System.out.println("Désolé, l'attribution a échoué car il n'y a pas d'emplacement de ce type de libre.");
	}

	private static void consulterExposant() {
		System.out.println("Entrez le nom de l'exposant à consulter :");
		String nom = scanner.nextLine();
		Exposant exposant = brocante.getExposant(nom);
		if (exposant == null) {
			System.out.println("Désolé, l'exposant n'existe pas");
			return;
		}
		System.out.println("Voici les informations sur l'exposant: " + exposant);
		System.out.println("Voici la liste des numéros d'emplacements dont il dispose :");
		System.out.println(emplacementsExposant(exposant));
	}

	private static void tousLesExposants(){
		if (brocante.estVide()) System.out.println("La brocante est vide.");
		else {
			Iterator<Exposant> exposants = brocante.tousLesExposants();
			while (exposants.hasNext()){
				System.out.println(exposants.next());
			}
		}
	}

	public static void libererEmplacement(){
		if (brocante.estVide()) System.out.println("La brocante est déjà vide.");
		System.out.println("Entrez le nom de l'exposant :");
		String nom = scanner.nextLine();
		Exposant exposant = brocante.getExposant(nom);

		if (exposant == null) {
			System.out.println("Désolé, l'exposant n'est pas répertorié dans la brocante");
			return;
		}

		System.out.println("Voici la liste des numéros d'emplacements dont l'exposant dispose :");
		System.out.println(emplacementsExposant(exposant));
		System.out.println("Entrez le numéro de l'emplacement à libérer :");
		int num = scanner.nextInt();

		Emplacement emplacement;

		try{
			emplacement = brocante.getEmplacement(num);
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			return;
		}

		if (emplacement.getExposant() == null || emplacement.getExposant() != exposant){
			System.out.println("L'exposant ne détient pas cet emplacement !");
			return;
		}

		if (brocante.libererEmplacement(nom,num)) System.out.println("La libération a réussi.");
		else System.out.println("La libération a échoué."); // normalement, avec tous les tests de validité,
		// l'utilisateur ne devrait jamais recevoir ce message. mais je le garde au cas ou il y a une exception ou un bug

	}

	private static String emplacementsExposant(Exposant exposant) {
		Iterator<Emplacement> emplacements = exposant.touslesEmplacements();
		String res = "[";
		while (emplacements.hasNext()){
			res += emplacements.next().getNumero();
			if (emplacements.hasNext()) res += ", ";
		}
		res += "]";
		return res;
	}

	private static void afficherTout() {
		System.out.println(brocante);		
	}

}
