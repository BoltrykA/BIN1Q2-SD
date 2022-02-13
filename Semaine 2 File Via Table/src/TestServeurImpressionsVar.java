import static java.lang.System.out;

public class TestServeurImpressionsVar {
	public static void main(String[] args) {
		ServeurImpressionsVar file = new ServeurImpressionsVar();
		DemandeImpressionAvecPriorite demande;
		try {
			System.out.println("ajout dans une file vide des documents suivants (dans cet ordre!)");
			System.out.println("doc1 - 9");
			file.ajouter(new DemandeImpressionAvecPriorite("doc1", 9));
			System.out.println("doc2 - 8");
			file.ajouter(new DemandeImpressionAvecPriorite("doc2", 8));
			System.out.println("doc3 - 7");
			file.ajouter(new DemandeImpressionAvecPriorite("doc3", 7));
			System.out.println("doc4 - 6");
			file.ajouter(new DemandeImpressionAvecPriorite("doc4", 6));
			System.out.println("doc5 - 9");
			file.ajouter(new DemandeImpressionAvecPriorite("doc5", 9));
			System.out.println("doc6 - 8");
			file.ajouter(new DemandeImpressionAvecPriorite("doc6", 8));
			System.out.println("doc7 - 9");
			file.ajouter(new DemandeImpressionAvecPriorite("doc7", 9));
			System.out.println("doc8 - 9");
			file.ajouter(new DemandeImpressionAvecPriorite("doc8", 9));
			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc1")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc1 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc1 : OK") ;
			}
			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc5")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc5 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc5 : OK") ;
			}
			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc7")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc7 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc7 : OK") ;
			}
			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc8")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc8 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc8 : OK") ;
			}
			System.out.println("ajout des documents suivants (dans cet ordre!)");
			System.out.println("doc9 - 9");
			file.ajouter(new DemandeImpressionAvecPriorite("doc9", 9));
			System.out.println("doc10 - 8");
			file.ajouter(new DemandeImpressionAvecPriorite("doc10", 8));

			System.out.print("retrait d'1 document : ");

			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc2")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc2 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc2 : OK") ;
			}
			
			System.out.print("retrait d'1 document : ");

			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc9")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc9 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc9 : OK") ;
			}
			
			System.out.print("retrait d'1 document : ");

			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc6")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc6 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc6 : OK") ;
			}

			System.out.print("retrait d'1 document : ");

			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc3")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc3 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc3 : OK") ;
			}
			System.out.println("doc11 - 8");
			file.ajouter(new DemandeImpressionAvecPriorite("doc11", 8));

			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc10")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc10 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc10 : OK") ;
			}

			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc4")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc4 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc4 : OK") ;
			}

			System.out.println("ajout dans une file du document suivant");
			System.out.println("doc12 - 1");
			file.ajouter(new DemandeImpressionAvecPriorite("doc12", 1));

			System.out.print("retrait d'1 document : ");
			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc11")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc11 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc11 : OK") ;
			}
			
			System.out.print("retrait d'1 document : ");

			demande = file.retirer();
			if (!demande.getNomDocument().equals("doc12")) {
				System.out.println("Attention le document retire est " + demande.getNomDocument());
				System.out.println("C'est le document doc12 qui aurait du etre retire!");
				return;
			} else {
				System.out.println("doc12 : OK") ;
			}
			
			try {
				System.out.print("Retrait dans une pile vide : ");
				demande = file.retirer();
				System.out.println("Il fallait une exception ! ");
			} catch (FileVideException e) {
				System.out.println("OK");
			}
			
		} catch (FileVideException e) {
			System.out.println("Attention FileVideException" + e.getMessage());
		}

		System.out.println("Tous les tests sont OK!");

	}
}
