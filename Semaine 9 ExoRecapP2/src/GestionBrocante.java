import java.util.Scanner;


public class GestionBrocante {
	
	private static MonScanner scanner = new MonScanner("lancement.txt");
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
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain "+ (i+1) + ": ");
			tableRiverains[i] = scanner.nextLine();
		}
		brocante = new Brocante(nombreEmplacements, tableRiverains);
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
		
		System.out.println("Phase 2 -- N'importe quel exposant et autant d'emplacements que souhait�");
		System.out.println("-------");
		choix = 0;
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
				reserverPhase2();
				break;
			case 2:
				afficherTout();
				break;
			}

		} while (choix >= 1 && choix <= 2);
		
		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		if (!brocante.estLibre(numero)){
			System.out.println("D�sol�, cet emplacement n'est pas disponible ou inexistant.");
			return;
		}
		scanner.nextLine();
		System.out.print("Entrez le nom du demandeur: ");
		String nom = scanner.nextLine();
		if (!brocante.estUnRiverain(nom)){
			System.out.println("D�sol�, l'exposant n'est pas un riverain.");
			return;
		}
		if (brocante.nombreEmplacementsRiverain(nom) == 3){
			System.out.println("Ce riverain a d�j� trois emplacements de r�serv�s !");
		}
		System.out.println("Entrez son adresse e-mail: ");
		String email = scanner.nextLine();
		System.out.println("Entrez son num�ro de t�l�phone: ");
		String numTel = scanner.nextLine();
		Exposant exposant = new Exposant(nom,email,numTel);

		boolean aReussi = false;
		try {
			aReussi = brocante.reserver(exposant, numero);
		} catch (IllegalArgumentException | IllegalStateException e){
			System.out.println(e.getMessage());
		}

		if (aReussi) System.out.println("L'attribution a r�ussi.");
		else System.out.println("L'attribution n'a pas abouti.");
		
	}

	private static void reserverPhase2() {
		if (!brocante.emplacementLibre()){
			System.out.println("D�sol�, la brocante est pleine !");
			return;
		}
		System.out.print("Entrez le nom du demandeur: ");
		String nom = scanner.nextLine();
		System.out.println("Entrez son adresse e-mail: ");
		String email = scanner.nextLine();
		System.out.println("Entrez son num�ro de t�l�phone: ");
		String numTel = scanner.nextLine();
		Exposant exposant = new Exposant(nom,email,numTel);
		int numero = brocante.attribuerAutomatiquementEmplacement(exposant);

		if (numero != -1) System.out.println(nom + " s'est fait attribuer l'emplacement num�ro " + numero);
		else System.out.println("L'attribution a �chou�.");
	}

	private static void afficherTout() {
		System.out.println(brocante);		
	}

}
