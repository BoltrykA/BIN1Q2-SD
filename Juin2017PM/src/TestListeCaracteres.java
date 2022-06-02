import java.util.Scanner;

public class TestListeCaracteres {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a reçu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println();
				System.out.println("     "+messageErreur+" attendu="+attendu+" reçu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println();
			System.out.println("    "+messageErreur+" attendu="+attendu+" reçu="+recu);
			System.exit(0);			
		}
	}

	public static void main(String[] args) {

		System.out.println("*********************************************");
		System.out.println("Programme Test pour la classe ListeCaracteres");
		System.out.println("*********************************************");
		int choix = 0;
		do {
			System.out.println("1 -> Tester la methode inserer");
			System.out.println("2 -> Tester la methode contientExAequo");
			System.out.println("3 -> Tester la methode contient");
			System.out.println("4 -> Tester la methode nombreCaracteresTable");
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
			case 1:
				testInserer();
				break;
			case 2:
				testContientExAequo();
				break;
			case 3:
				testContient();
				break;
			case 4:
				testNombreCaracteresTable();
				break;
			default:
				break;
			}
		} while (choix >= 1 && choix <= 4 );
	}



	private static void testInserer() {
		char [] caracteres = {'a','b','c','d'};	
		ListeCaracteres l = new ListeCaracteres(caracteres);
		System.out.print("Test 1 : liste testee : "+l +"     appel methode : inserer(1,x)");
		try {
			l.inserer(1, 'x');
			assertEquals("ko : liste apres insertion : "," a x b c d",l.toString());
			System.out.println(" : ok");
		} catch (Exception e) {
			System.out.println("ko : il y a eu exception : ");
			e.printStackTrace();
			return;
		}
	
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 2 : liste testee : "+l +"     appel methode : inserer(2,x)");
		try {
			l.inserer(2, 'x');
			assertEquals("ko : liste apres insertion : "," a b x c d",l.toString());
			System.out.println(" : ok");
		} catch (Exception e) {
			System.out.println("ko : il y a eu exception : ");
			e.printStackTrace();
			return;
		}
	
		
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 3 : liste testee : "+l +"     appel methode : inserer(4,x)");
		try {
			l.inserer(4, 'x');
			assertEquals("ko : liste apres insertion : "," a b c d x",l.toString());
			System.out.println(" : ok");
		} catch (Exception e) {
			System.out.println("ko : il y a eu exception : ");
			e.printStackTrace();
			return;
		}
		
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 4 : liste testee : "+l +"     appel methode : inserer(0,x)");	
		try {
			l.inserer(0, 'x');
			assertEquals("ko : liste apres insertion : "," x a b c d",l.toString());
			System.out.println(" : ok");
		} catch (Exception e) {
			System.out.println("ko : il y a eu exception : ");
			e.printStackTrace();
			return;
		}

		l = new ListeCaracteres(caracteres);
		System.out.print("Test 5 : liste testee : "+l +"     appel methode : inserer(-1,x)");
		try {
			l.inserer(-1, 'x');
			System.out.println("ko : il n'y a pas eu IllegalArgumentException : ");
			return;
		} 
		catch (IllegalArgumentException e) {
			assertEquals("ko : liste apres insertion : "," a b c d",l.toString());
			System.out.println(" : ok");	
		}
		catch (Exception e) {
			System.out.println("ko : il y a eu exception : ");
			e.printStackTrace();
			return;
		}
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 6 : liste testee : "+l +"     appel methode : inserer(5,x)");
		try {
			l.inserer(5, 'x');
			System.out.println("ko : il n'y a pas eu IllegalArgumentException : ");
			return;
		} 
		catch (IllegalArgumentException e) {
			assertEquals("ko : liste apres insertion : "," a b c d",l.toString());
			System.out.println(" : ok");	
		}
		catch (Exception e) {
			System.out.println("ko : il y a eu exception : ");
			e.printStackTrace();
			return;
		}


		l = new ListeCaracteres();
		System.out.print("Test 7 : liste vide :                appel methode : inserer(0,x)");	
		try {
			l.inserer(0, 'x');
			assertEquals("ko : liste apres insertion : "," x",l.toString());
			System.out.println(" : ok");
		} catch (Exception e) {

		}


		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();
		
	}

	private static void testContientExAequo() {
		char [] caracteres1 = {'a','b','c','d'};	
		ListeCaracteres l = new ListeCaracteres(caracteres1);
		System.out.print("Test 1 : liste testee : "+l +"     appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",false,l.contientExAequos());
		assertEquals("ko : liste apres test : "," a b c d",l.toString());
		System.out.println(" : ok");
		char [] caracteres2 = {'a','b','a','d'};	
		l = new ListeCaracteres(caracteres2);
		System.out.print("Test 2 : liste testee : "+l +"     appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",true,l.contientExAequos());
		System.out.println(" : ok");
		char [] caracteres3 = {'a','b','c','a'};	
		l = new ListeCaracteres(caracteres3);
		System.out.print("Test 3 : liste testee : "+l +"     appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",true,l.contientExAequos());
		System.out.println(" : ok");
		l = new ListeCaracteres();
		System.out.print("Test 4 : liste vide :                appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",false,l.contientExAequos());
		System.out.println(" : ok");
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();		
	}

	private static void testContient(){
		char [] caracteres = {'a','b','c','d'};
		ListeCaracteres l;
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 1 : liste testee : "+l+" contient c : ");
		assertEquals("booleen renvoye ko",true,l.contient('c'));
		assertEquals("Attention, liste modifiee! "," a b c d",l.toString());
		System.out.println("ok");
		
	
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 2 : liste testee : "+l+ " contient f : ");
		assertEquals("booleen renvoye ko ",false,l.contient('f'));
		System.out.println("ok");

	
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 3 : liste testee : "+l+" contient d : ");
		assertEquals("booleen renvoye ko ",true, l.contient('d'));
		System.out.println("ok");
		
	
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 4 : liste testee : "+l+" contient a : ");
		assertEquals("booleen renvoye ko ",true, l.contient('a'));
		System.out.println("ok");
		
		
		l = new ListeCaracteres();
		System.out.print("Test 5 : liste vide : contient a : ");
		assertEquals("booleen renvoye ko ",false,l.contient('a'));
		System.out.println("ok");

		char [] caractereA = {'a'};
		l = new ListeCaracteres(caractereA);
		System.out.print("Test 6 : liste testee : "+l+" contient a : ");
		assertEquals("booleen renvoye ko ",true,l.contient('a'));
		System.out.println("ok");
		
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();

	}
	private static void testNombreCaracteresTable() {
		char [] caracteres1 = {'a','b','c','d'};
		char [] voyelles = {'a','e','i','o','u','y'};
		ListeCaracteres l = new ListeCaracteres(caracteres1);
		System.out.print("Test 1 : liste testee : "+l +"     appel methode : nombreCaracteresTable()");	
		assertEquals("ko : entier renvoye",1,l.nombreCaracteresTable(voyelles));
		assertEquals("ko : liste apres test : "," a b c d",l.toString());
		System.out.println(" : ok");
		char [] caracteres2 = {'b','c','d','i'};	
		l = new ListeCaracteres(caracteres2);
		System.out.print("Test 2 : liste testee : "+l +"     appel methode : nombreCaracteresTable()");	
		assertEquals("ko : entier renvoye",1,l.nombreCaracteresTable(voyelles));
		System.out.println(" : ok");
		char [] caracteres3 = {'b','o','o','d'};	
		l = new ListeCaracteres(caracteres3);
		System.out.print("Test 3 : liste testee : "+l +"     appel methode : nombreCaracteresTable()");	
		assertEquals("ko : entier renvoye",2,l.nombreCaracteresTable(voyelles));
		System.out.println(" : ok");
		char [] caracteres4 = {'b','c','d'};	
		l = new ListeCaracteres(caracteres4);
		System.out.print("Test 4 : liste testee : "+l +"       appel methode : nombreCaracteresTable()");	
		assertEquals("ko : entier renvoye",0,l.nombreCaracteresTable(voyelles));
		System.out.println(" : ok");
		l = new ListeCaracteres();
		System.out.print("Test 5 : liste vide :                appel methode : nombreCaracteresTable()");	
		assertEquals("ko : entier renvoye",0,l.nombreCaracteresTable(voyelles));
		System.out.println(" : ok");
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();
	}

	

	

}
