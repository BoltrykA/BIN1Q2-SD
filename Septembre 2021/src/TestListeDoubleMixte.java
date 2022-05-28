import java.util.Scanner;

public class TestListeDoubleMixte{
	
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a recu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
			
				System.out.println(messageErreur+"\n --> Attendu="+attendu+" recu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
		
			System.out.println(messageErreur+"\n --> Attendu="+attendu+" recu="+recu);
			System.exit(0);			
		}
	}
	
	/**
	 * Cette methode appelle la methode assertEquals avec un message d'erreur adequat
	 * @param numeroMessage le numero du message a afficher en cas d'erreur
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a recu en realite
	 */
	private static void assertEquals(int numeroMessage, Object attendu, Object recu) {
		String[] message = new String[10];
		message[0]="Test ko, la methode n'a pas renvoye ce qui etait attendu";
		message[1]="Test ko, apres appel de la methode, le nombre d'elements dans le map n'est pas celui attendu";
		message[2]="Test ko, apres appel de la methode, Il y a un probleme dans le chainage dans le sens -->";
		message[3]="Test ko, apres appel de la methode, Il y a un probleme dans le chainage dans le sens <--";
		message[4]="Test ko, apres appel de la methode, la liste a ete modifiee";
		assertEquals(message[numeroMessage],attendu,recu);
	}
	
	public static void main(String[] args) {
		System.out.println("**********************************************");
		System.out.println("Programme Test pour la classe ListeDoubleMixte");
		System.out.println("**********************************************");
		int choix = 0;
		do {	
			System.out.println("1 -> Tester la methode auMoins1Belge()");
			System.out.println("2 -> Tester la methode nombreNationalitesDifferentes()");				
			System.out.println("3 -> Tester la methode estPresent()");
			System.out.println("4 -> Tester la methode supprimerCouple()");	
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {		
			case 1:
				testAuMoins1Belge();
				break;	
			case 2:
				testNombreNationalitesDifferentes();
				break;
			case 3:
				testEstPresent();
				break;
			case 4:
				testSupprimerCouple();
				break;

			default:
				break;
			}
		} while (choix >= 1 && choix <= 4 );	
		System.out.println();
		System.out.println("Fin des tests");
	}

	private static void testAuMoins1Belge() {
		ListeDoubleMixte listeTournoi ;
		System.out.println();
	
		JoueurTennis[] tableTestee = new JoueurTennis[6];
		tableTestee[0] = new JoueurTennis("adam",'H',"B");
		tableTestee[1] = new JoueurTennis("eve",'F',"B");
		tableTestee[2] = new JoueurTennis("william",'H',"B");
		tableTestee[3] = new JoueurTennis("kate",'F',"B");
		tableTestee[4] = new JoueurTennis("serge",'H',"B");
		tableTestee[5] = new JoueurTennis("jane",'F',"B");
		
		System.out.println();
		System.out.println("Test1 : liste testee : adam(B) eve(B) william(B) kate(B) serge(B) jane(B)");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		try{
			assertEquals(0, true, listeTournoi.auMoins1Belge());
			assertEquals(4, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
			System.out.println("Test ok");
		}catch (NullPointerException e){
			System.out.println();
			System.out.println("Il y a eu une NullPointerException");
			System.out.println("Avez-vous bien pense au fait que la liste possede des sentinelles ?");
			System.out.println();
			e.printStackTrace();
			System.exit(0);
			
		}catch (Exception e){
			System.out.println();
			System.out.println("Il y a eu une Exception");
			System.out.println("Avez-vous bien pense au fait que la liste possede des sentinelles ?");
			System.out.println();
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam(F) eve(F) william(F) kate(F) serge(F) jane(B)");
		tableTestee[0] = new JoueurTennis("adam",'H',"F");
		tableTestee[1] = new JoueurTennis("eve",'F',"F");
		tableTestee[2] = new JoueurTennis("william",'H',"F");
		tableTestee[3] = new JoueurTennis("kate",'F',"F");
		tableTestee[4] = new JoueurTennis("serge",'H',"F");
		tableTestee[5] = new JoueurTennis("jane",'F',"B");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		assertEquals(0, true, listeTournoi.auMoins1Belge());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam(F) eve(F) william(F) kate(F) serge(F) jane(F)");
		tableTestee[0] = new JoueurTennis("adam",'H',"F");
		tableTestee[1] = new JoueurTennis("eve",'F',"F");
		tableTestee[2] = new JoueurTennis("william",'H',"F");
		tableTestee[3] = new JoueurTennis("kate",'F',"F");
		tableTestee[4] = new JoueurTennis("serge",'H',"F");
		tableTestee[5] = new JoueurTennis("jane",'F',"F");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		assertEquals(0, false, listeTournoi.auMoins1Belge());
		System.out.println("Test ok");
			
		System.out.println();
		System.out.println("Test4 : liste testee : adam(B) eve(F) william(F) kate(F) serge(F) jane(F)");
		tableTestee[0] = new JoueurTennis("adam",'H',"B");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		assertEquals(0, true, listeTournoi.auMoins1Belge());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test5 : liste testee : liste vide");
		listeTournoi = new ListeDoubleMixte();	
		assertEquals(0, false, listeTournoi.auMoins1Belge());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();
		
	}

	private static void testNombreNationalitesDifferentes() {
		ListeDoubleMixte listeTournoi ;
		System.out.println();
	
		JoueurTennis[] tableTestee = new JoueurTennis[6];
		tableTestee[0] = new JoueurTennis("adam",'H',"B");
		tableTestee[1] = new JoueurTennis("eve",'F',"B");
		tableTestee[2] = new JoueurTennis("william",'H',"B");
		tableTestee[3] = new JoueurTennis("kate",'F',"B");
		tableTestee[4] = new JoueurTennis("serge",'H',"B");
		tableTestee[5] = new JoueurTennis("jane",'F',"B");
		
		System.out.println();
		System.out.println("Test1 : liste testee : adam(B) eve(B) william(B) kate(B) serge(B) jane(B)");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		try{
		assertEquals(0, 1 , listeTournoi.nombreNationalitesDifferentes());
		assertEquals(4, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		System.out.println("Test ok");
		}
		catch(NullPointerException e){
			System.out.println();
			System.out.println("Il y a eu une NullPointerException");
			System.out.println("Avez-vous bien pense au fait que la liste possede des sentinelles ?");
			System.out.println();
			e.printStackTrace();
			System.exit(0);
			
		}catch (Exception e){
			System.out.println();
			System.out.println("Il y a eu une Exception");
			System.out.println("Avez-vous bien pense au fait que la liste possede des sentinelles ?");
			System.out.println();
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println();
		System.out.println("Test2 : liste testee : adam(CH) eve(B) william(B) kate(B) serge(B) jane(F) ");
		tableTestee[0] = new JoueurTennis("adam",'H',"CH");
		tableTestee[5] = new JoueurTennis("jane",'F',"F");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		assertEquals(0, 3 , listeTournoi.nombreNationalitesDifferentes());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam(CH) eve(F) william(B) kate(B) serge(F) jane(F)");
		tableTestee[1] = new JoueurTennis("eve",'F',"F");
		tableTestee[4] = new JoueurTennis("serge",'H',"F");
		listeTournoi = new ListeDoubleMixte(tableTestee);	
		assertEquals(0, 3 , listeTournoi.nombreNationalitesDifferentes());
		System.out.println("Test ok");	
		
		System.out.println();
		System.out.println("Test4 : liste testee : liste vide");
		listeTournoi = new ListeDoubleMixte();	
		assertEquals(0, 0 , listeTournoi.nombreNationalitesDifferentes());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();
		
		
	}

	


	private static void testEstPresent() {
		ListeDoubleMixte listeDoubleMixte ;
		System.out.println();
			
		JoueurTennis[] tableTestee = new JoueurTennis[6];
		tableTestee[0] = new JoueurTennis("adam",'H',"B");
		tableTestee[1] = new JoueurTennis("eve",'F',"B");
		tableTestee[2] = new JoueurTennis("william",'H',"B");
		tableTestee[3] = new JoueurTennis("kate",'F',"B");
		tableTestee[4] = new JoueurTennis("serge",'H',"B");
		tableTestee[5] = new JoueurTennis("jane",'F',"B");
		
		
		JoueurTennis william = new JoueurTennis("william",'H',"B");
		JoueurTennis eve = new JoueurTennis("eve",'F',"B");
		JoueurTennis jane = new JoueurTennis("jane",'F',"B");
		JoueurTennis pierre = new JoueurTennis("pierre",'H',"B");
		
		
		System.out.println();
		System.out.println("Test1 : liste testee : adam eve william kate serge jane : estPresent william");
		listeDoubleMixte = new ListeDoubleMixte(tableTestee);	
		assertEquals(0, true, listeDoubleMixte.estPresent(william));
		assertEquals(4, "(adam,eve,william,kate,serge,jane)", listeDoubleMixte.teteQueue());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam eve william kate serge jane : estPresent jane");	
		listeDoubleMixte = new ListeDoubleMixte(tableTestee);
		assertEquals(0, true, listeDoubleMixte.estPresent(jane));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam eve william kate serge jane : estPresent pierre");	
		listeDoubleMixte = new ListeDoubleMixte(tableTestee);
		assertEquals(0, false, listeDoubleMixte.estPresent(pierre));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test4 : liste testee : liste vide : estPresent eve");	
		listeDoubleMixte = new ListeDoubleMixte();
		assertEquals(0, false, listeDoubleMixte.estPresent(eve));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();

	}

	private static void testSupprimerCouple() {
		
		ListeDoubleMixte listeTournoi ;
		System.out.println();
			
		JoueurTennis[] tableTestee = new JoueurTennis[6];
		tableTestee[0] = new JoueurTennis("adam",'H',"B");
		tableTestee[1] = new JoueurTennis("eve",'F',"B");
		tableTestee[2] = new JoueurTennis("william",'H',"B");
		tableTestee[3] = new JoueurTennis("kate",'F',"B");
		tableTestee[4] = new JoueurTennis("serge",'H',"B");
		tableTestee[5] = new JoueurTennis("jane",'F',"B");
		JoueurTennis kate = new JoueurTennis("kate",'F',"B");
		JoueurTennis william = new JoueurTennis("william",'H',"B");
		JoueurTennis eve = new JoueurTennis("eve",'F',"B");
		JoueurTennis adam = new JoueurTennis("adam",'H',"B");
		JoueurTennis jane = new JoueurTennis("jane",'F',"B");
		JoueurTennis serge = new JoueurTennis("serge",'H',"B");
		JoueurTennis pierre = new JoueurTennis("pierre",'H',"B");
			
		System.out.println();
		System.out.println("Test1 : liste testee : adam eve william kate serge jane : supprimerCouple william");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, true, listeTournoi.supprimerCouple(william));
		assertEquals(1, 4, listeTournoi.taille());
		assertEquals(2, "(adam,eve,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam eve william kate serge jane : supprimerCouple kate");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, true, listeTournoi.supprimerCouple(kate));
		assertEquals(1, 4, listeTournoi.taille());
		assertEquals(2, "(adam,eve,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam eve william kate serge jane : supprimerCouple adam");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, true, listeTournoi.supprimerCouple(adam));
		assertEquals(1, 4, listeTournoi.taille());
		assertEquals(2, "(william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test4 : liste testee : adam eve william kate serge jane : supprimerCouple eve");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, true, listeTournoi.supprimerCouple(eve));
		assertEquals(1, 4, listeTournoi.taille());
		assertEquals(2, "(william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test5 : liste testee : adam eve william kate serge jane : supprimerCouple serge");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, true, listeTournoi.supprimerCouple(serge));
		assertEquals(1, 4, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate)", listeTournoi.teteQueue());
		assertEquals(3, "(kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test6 : liste testee : adam eve william kate serge jane : supprimerCouple jane");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, true, listeTournoi.supprimerCouple(jane));
		assertEquals(1, 4, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate)", listeTournoi.teteQueue());
		assertEquals(3, "(kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test7 : liste testee : adam eve william kate serge jane : supprimerCouple pierre");
	
		listeTournoi = new ListeDoubleMixte(tableTestee);
		
		assertEquals(0, false, listeTournoi.supprimerCouple(pierre));
		assertEquals(1, 6, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test8 : liste testee : liste vide : supprimerCouple jane");
		
		listeTournoi = new ListeDoubleMixte();
		
		assertEquals(0, false, listeTournoi.supprimerCouple(jane));
		assertEquals(1, 0, listeTournoi.taille());
		assertEquals(2, "()", listeTournoi.teteQueue());
		assertEquals(3, "()", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();
		
	}

}
