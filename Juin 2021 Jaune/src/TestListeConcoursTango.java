import java.util.Scanner;

public class TestListeConcoursTango{
	
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
		System.out.println("************************************************");
		System.out.println("Programme Test pour la classe ListeConcoursTango");
		System.out.println("************************************************");
		int choix = 0;
		do {	
			System.out.println("1 -> Tester la methode nombreDanseursDeNationalite()");
			System.out.println("2 -> Tester la methode ontTous3AnsMinimumDExperience()");			
			System.out.println("3 -> Tester la methode donnerPartenaire()");
			System.out.println("4 -> Tester la methode ajouterCouple()");	
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
			case 1:
				testNombreDanseursBloc();
				break;
			case 2:
				testTousEnInfo();
				break;		
			case 3:
				testDonnerPartenaire();
				break;
			case 4:
				testAjouterCouple();
				break;

			default:
				break;
			}
		} while (choix >= 1 && choix <= 4 );	
		System.out.println();
		System.out.println("Fin des tests");
	}


	private static void testNombreDanseursBloc() {
		ListeConcoursTango listeTournoi ;
		System.out.println();
	
		Danseur[] tableTestee = new Danseur[6];
		tableTestee[0] = new Danseur("adam",'H',"B",3);
		tableTestee[1] = new Danseur("eve",'F',"B",3);
		tableTestee[2] = new Danseur("william",'H',"B",3);
		tableTestee[3] = new Danseur("kate",'F',"B",3);
		tableTestee[4] = new Danseur("serge",'H',"B",3);
		tableTestee[5] = new Danseur("jane",'F',"B",3);
		
		System.out.println();
		System.out.println("Test1 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(B 3) serge(B 3) jane(B 3) --> B (belge)");
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, 6 , listeTournoi.donnerNombreDanseursDeNationalite("B"));
		assertEquals(4, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(B 3) serge(B 3) jane(B 3) --> CH (suisse)");
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, 0 , listeTournoi.donnerNombreDanseursDeNationalite("CH"));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(CH 2) serge(B 3) jane(F 3) --> B (belge)");
		tableTestee[3] = new Danseur("kate",'F',"CH",3);
		tableTestee[5] = new Danseur("jane",'F',"F",3);
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, 4 , listeTournoi.donnerNombreDanseursDeNationalite("B"));
		System.out.println("Test ok");	
		
		System.out.println();
		System.out.println("Test4 : liste testee : liste vide --> B (belge)");
		listeTournoi = new ListeConcoursTango();	
		assertEquals(0, 0 , listeTournoi.donnerNombreDanseursDeNationalite("B"));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();
		
		
	}

	private static void testTousEnInfo() {
		ListeConcoursTango listeTournoi ;
		System.out.println();
	
		Danseur[] tableTestee = new Danseur[6];
		tableTestee[0] = new Danseur("adam",'H',"B",3);
		tableTestee[1] = new Danseur("eve",'F',"B",3);
		tableTestee[2] = new Danseur("william",'H',"B",3);
		tableTestee[3] = new Danseur("kate",'F',"B",3);
		tableTestee[4] = new Danseur("serge",'H',"B",3);
		tableTestee[5] = new Danseur("jane",'F',"B",3);
		
		System.out.println();
		System.out.println("Test1 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(B 3) serge(B 3) jane(B 3)");
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, true, listeTournoi.ontTous3AnsMinimumDExperience());
		assertEquals(4, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(B 2) serge(B 3) jane(B 3)");
		tableTestee[3] = new Danseur("kate",'F',"B",2);
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, false, listeTournoi.ontTous3AnsMinimumDExperience());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam(B 1) eve(B 3) william(B 3) kate(B 3) serge(B 3) jane(B 3)");
		tableTestee[0] = new Danseur("adam",'H',"B",1);
		tableTestee[3] = new Danseur("kate",'F',"B",3);
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, false, listeTournoi.ontTous3AnsMinimumDExperience());
		System.out.println("Test ok");
			
		System.out.println();
		System.out.println("Test4 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(B 3) serge(B 3) jane(B 1)");
		tableTestee[0] = new Danseur("adam",'H',"B",3);
		tableTestee[5] = new Danseur("jane",'F',"B",1);
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, false, listeTournoi.ontTous3AnsMinimumDExperience());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test5 : liste testee : adam(B 3) eve(B 3) william(B 3) kate(B 3) serge(B 3) jane(B 4)");
		tableTestee[5] = new Danseur("jane",'F',"B",4);
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, true, listeTournoi.ontTous3AnsMinimumDExperience());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test6 : liste testee : liste vide");
		listeTournoi = new ListeConcoursTango();	
		assertEquals(0, true, listeTournoi.ontTous3AnsMinimumDExperience());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();
		
	}


	private static void testDonnerPartenaire() {
		ListeConcoursTango listeTournoi ;
		System.out.println();
			
		Danseur[] tableTestee = new Danseur[6];
		tableTestee[0] = new Danseur("adam",'H',"B",3);
		tableTestee[1] = new Danseur("eve",'F',"B",3);
		tableTestee[2] = new Danseur("william",'H',"B",3);
		tableTestee[3] = new Danseur("kate",'F',"B",3);
		tableTestee[4] = new Danseur("serge",'H',"B",3);
		tableTestee[5] = new Danseur("jane",'F',"B",3);
		
		Danseur kate = new Danseur("kate",'F',"B",3);
		Danseur william = new Danseur("william",'H',"B",3);
		Danseur eve = new Danseur("eve",'F',"B",3);
		Danseur adam = new Danseur("adam",'H',"B",3);
		Danseur jane = new Danseur("jane",'F',"B",3);
		Danseur serge = new Danseur("serge",'H',"B",3);
		Danseur pierre = new Danseur("pierre",'H',"B",3);
		
		
		System.out.println();
		System.out.println("Test1 : liste testee : adam eve william kate serge jane : donnerPartenaire william");
		listeTournoi = new ListeConcoursTango(tableTestee);	
		assertEquals(0, kate, listeTournoi.donnerPartenaire(william));
		assertEquals(4, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam eve william kate serge jane : donnerPartenaire kate");	
		listeTournoi = new ListeConcoursTango(tableTestee);
		assertEquals(0, william, listeTournoi.donnerPartenaire(kate));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam eve william kate serge jane : donnerPartenaire serge");	
		listeTournoi = new ListeConcoursTango(tableTestee);
		assertEquals(0, jane, listeTournoi.donnerPartenaire(serge));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test4 : liste testee : adam eve william kate serge jane : donnerPartenaire eve");	
		listeTournoi = new ListeConcoursTango(tableTestee);
		assertEquals(0, adam, listeTournoi.donnerPartenaire(eve));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test5 : liste testee : adam eve william kate serge jane : donnerPartenaire pierre");	
		listeTournoi = new ListeConcoursTango(tableTestee);
		assertEquals(0, null, listeTournoi.donnerPartenaire(pierre));
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();

	}

	private static void testAjouterCouple() {
		
		ListeConcoursTango listeTournoi ;
		System.out.println();
			
		Danseur[] tableTestee = new Danseur[6];
		tableTestee[0] = new Danseur("adam",'H',"B",3);
		tableTestee[1] = new Danseur("eve",'F',"B",3);
		tableTestee[2] = new Danseur("william",'H',"B",3);
		tableTestee[3] = new Danseur("kate",'F',"B",3);
		tableTestee[4] = new Danseur("serge",'H',"B",3);
		tableTestee[5] = new Danseur("jane",'F',"B",3);
			
		System.out.println();
		System.out.println("Test1 : liste testee : adam eve william kate serge jane : ajouterCouple philippe mathilde");
	
		listeTournoi = new ListeConcoursTango(tableTestee);
		
		assertEquals(0, true, listeTournoi.ajouterCouple(new Danseur("philippe", 'H', "B",3),new Danseur("mathilde",'F',"B",3)));
		assertEquals(1, 8, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane,philippe,mathilde)", listeTournoi.teteQueue());
		assertEquals(3, "(mathilde,philippe,jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test2 : liste testee : adam eve william kate serge jane : ajouterCouple mathilde philippe");
	
		listeTournoi = new ListeConcoursTango(tableTestee);
		
		assertEquals(0, true, listeTournoi.ajouterCouple(new Danseur("mathilde",'F',"B",3),new Danseur("philippe", 'H', "B",3)));
		assertEquals(1, 8, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane,philippe,mathilde)", listeTournoi.teteQueue());
		assertEquals(3, "(mathilde,philippe,jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test3 : liste testee : adam eve william kate serge jane : ajouterCouple william marie");
	
		listeTournoi = new ListeConcoursTango(tableTestee);
		
		assertEquals(0, false, listeTournoi.ajouterCouple(new Danseur("william",'H',"B",3),new Danseur("marie", 'F', "B",3)));
		assertEquals(1, 6, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		
		System.out.println();
		System.out.println("Test4 : liste testee : adam eve william kate serge jane : ajouterCouple pierre jane");
	
		listeTournoi = new ListeConcoursTango(tableTestee);
		
		assertEquals(0, false, listeTournoi.ajouterCouple(new Danseur("pierre",'H',"B",3),new Danseur("jane", 'F', "B",3)));
		assertEquals(1, 6, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test5 : liste testee : adam eve william kate serge jane : ajouterCouple pierre paul");
	
		listeTournoi = new ListeConcoursTango(tableTestee);
		
		assertEquals(0, false, listeTournoi.ajouterCouple(new Danseur("pierre",'H',"B",3),new Danseur("paul", 'H', "B",3)));
		assertEquals(1, 6, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test6 : liste testee : adam eve william kate serge jane : ajouterCouple anne marie");
	
		listeTournoi = new ListeConcoursTango(tableTestee);
		
		assertEquals(0, false, listeTournoi.ajouterCouple(new Danseur("anne",'F',"B",3),new Danseur("marie", 'F', "B",3)));
		assertEquals(1, 6, listeTournoi.taille());
		assertEquals(2, "(adam,eve,william,kate,serge,jane)", listeTournoi.teteQueue());
		assertEquals(3, "(jane,serge,kate,william,eve,adam)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test7 : liste testee : liste vide : ajouterCouple philippe mathilde");
		
		listeTournoi = new ListeConcoursTango();
		
		assertEquals(0, true, listeTournoi.ajouterCouple(new Danseur("philippe",'H',"B",3),new Danseur("mathilde", 'F', "B",3)));
		assertEquals(1, 2, listeTournoi.taille());
		assertEquals(2, "(philippe,mathilde)", listeTournoi.teteQueue());
		assertEquals(3, "(mathilde,philippe)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Test8 : liste testee : liste vide : ajouterCouple mathilde philippe");
		
		listeTournoi = new ListeConcoursTango();
		
		assertEquals(0, true, listeTournoi.ajouterCouple(new Danseur("mathilde", 'F', "B",3),new Danseur("philippe",'H',"B",3)));
		assertEquals(1, 2, listeTournoi.taille());
		assertEquals(2, "(philippe,mathilde)", listeTournoi.teteQueue());
		assertEquals(3, "(mathilde,philippe)", listeTournoi.queueTete());
		System.out.println("Test ok");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi");
		System.out.println();
		System.out.println();
		
	}

}
