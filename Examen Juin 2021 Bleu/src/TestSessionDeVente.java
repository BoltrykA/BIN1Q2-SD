import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.lang.reflect.Field;

public class TestSessionDeVente {
	
	private static Scanner scanner = new Scanner(System.in);
	private static int temps = 300 ;
	private static	Class	classe =	SessionDeVente.class;
	private static	Field	fileA ;
	private static	Field	ensClM ;
	private static	Field	mapCC ;
	private static  Field   listeC ;
	private static  Field   nbCom ;
	private static String[] NOMS_METHODES = {"placerDansFileAttente", "selectionnerClientSuivant","passerNouvelleCommande","modifierCommande()"};

	private static	void preparerField()	{
		Field[] champs	= classe.getDeclaredFields();
		for (Field f :	champs) {
			if (f.getName().equals("fileAttente")) {
				fileA = f;
				fileA.setAccessible(true);
			} else if (f.getName().equals("ensembleClientsActuellementDansFile")) {
				ensClM = f;
				ensClM.setAccessible(true);
			} else if (f.getName().equals("mapClientCommande")) {
				mapCC = f;
				mapCC.setAccessible(true);	
			} else if (f.getName().equals("listeCommandes")) {
				listeC = f;
				listeC.setAccessible(true);	
			} else if (f.getName().equals("nombreCasiersRestants")) {
				nbCom = f;
				nbCom.setAccessible(true);	
			}
		}
	}	

	public static void main(String[] args) throws IllegalAccessException {
		System.out.println("************************************************");
		System.out.println("* Programme Test pour la classe SessionDeVente *");
		System.out.println("************************************************");
		preparerField();
		int choix = 0;
		do {			
			System.out.println("1 -> Tester la methode placerDansFileAttente()");	
			System.out.println("2 -> Tester la methode selectionnerClientSuivant()");
			System.out.println("3 -> Tester la methode passerNouvelleCommande");
			System.out.println("4 -> Tester la methode modifierCommande()");	
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			boolean testOK = true ;
			switch (choix) {
			case 1:
				testOK = testPlacerDansFileAttente() ;
				break;
			case 2:
				testOK = testSelectionnerClientSuivant() ;
				break;
			case 3:
				testOK = testerPasserNouvelleCommande() ;
				break;
			case 4:
				testOK = testerModifierCommande() ;
				break;

			default:
				return;
			}
			if (testOK)
				System.out.println("Les tests de la méthode " + NOMS_METHODES[choix - 1]
						+ " ont réussi.");
			else
				System.out.println("Les tests de la méthode " + NOMS_METHODES[choix - 1]
						+ " ont échoué.");
			System.out.println();
		} while (choix >= 1 && choix <= 4 );	


	}
	
	private static boolean testPlacerDansFileAttente() {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			session.placerDansFileAttente(null) ;
			System.out.println("KO : Il fallait une exception car le client est null") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.placerDansFileAttente(new String()) ;
			System.out.println("KO : Il fallait une exception car le client est vide") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException");
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    fileAttente.addLast("loic") ;
		    ensFA.add("loic") ;
			if (session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre méthode renvoie true mais le client était déjà dans la file") ;
				testOK = false ;
			} else {
				if (fileAttente.size()!=1 || !fileAttente.getFirst().equals("loic")) {
					System.out.println("KO : La liste a été modifiée mais le client était déjà dans la file") ;
					testOK = false ;
				} else if (ensFA.size()!=1 || !ensFA.contains("loic")) {
					System.out.println("KO : L'ensemble des clients a été modifié mais le client était déjà dans la file") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;

		System.out.print("Test 4 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    fileAttente.add("mathias");
			ensFA.add("mathias") ;
			ArrayDeque<String> fileAAttendue = fileAttente.clone() ;
			HashSet<String> ensAttendu = (HashSet<String>) ensFA.clone() ; 
		    nbCom.setInt(session,0);
			if (session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre méthode renvoie true mais il n'y avait plus de casier disponible") ;
				testOK = false ;
			} else {
				if (!fileAttente.toString().equals(fileAAttendue.toString())) {
					System.out.println("KO : La liste a été modifiée mais il n'y avait plus de casier de libre") ;
					testOK = false ;
				} else if (!ensFA.toString().equals(ensAttendu.toString())) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'y avant plus de casier de libre") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 5 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
			fileAttente.add("mathias");
			ensFA.add("mathias") ;
			ArrayDeque<String> fileAAttendue = fileAttente.clone() ;
			HashSet<String> ensAttendu = (HashSet<String>) ensFA.clone() ;
			Commande commande = new Commande("loic",SessionDeVente.MAX_CASIERS_CLIENT) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande) ;
			if (session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre méthode renvoie true mais le client avait déjà le max de casier") ;
				testOK = false ;
			} else {
				if (!fileAttente.toString().equals(fileAAttendue.toString())) {
					System.out.println("KO : La liste a été modifiée mais le client avait déjà le max de casier") ;
					testOK = false ;
				} else if (!ensFA.toString().toString().equals(ensAttendu.toString())) {
					System.out.println("KO : L'ensemble des clients dans la file a été modifié mais le client avait déjà le max de casier") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 6 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
		    fileAttente.addLast("mathias") ;
		    ensFA.add("mathias") ;
			if (!session.placerDansFileAttente(new String("loic"))) {
				testOK = false ;
				System.out.println("KO : Votre méthode renvoie false mais le client aurait dû être mis dans la file") ;
			} else {
				if (!fileAttente.toString().equals("[mathias, loic]")) {
					System.out.println("KO : file attendue : [mathias, loic] mais file reçue : "+fileAttente) ;
					testOK = false ;
				} else if (!ensFA.contains("loic")) {
					System.out.println("KO : Vous n'avez pas ajouté le client dans l'ensemble des clients dans la file") ;
					testOK = false ;
				} else if (ensFA.size()!=2 || !ensFA.contains("mathias")) {
					System.out.println("KO : Vous n'avez pas modifié correctement l'ensemble des clients dans la file : ens attendu = [mathias, loic] mais ens reçu = "+ensFA) ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 7 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
			Commande commande = new Commande("loic",SessionDeVente.MAX_CASIERS_CLIENT-1) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande) ;
		    fileAttente.addLast("mathias") ;
		    ensFA.add("mathias") ;
			if (!session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre méthode renvoie false mais le client aurait dû être mis dans la file") ;
				testOK = false ;
			} else {
				if (!fileAttente.toString().equals("[mathias, loic]")) {
					System.out.println("KO : file attendue : [mathias, loic] mais file reçue : "+fileAttente) ;
					testOK = false ;
				} else if (!ensFA.contains("loic")) {
					System.out.println("KO : Vous n'avez pas ajouté le client dans l'ensemble des clients dans la file") ;
					testOK = false ;
				} else if (ensFA.size()!=2 || !ensFA.contains("mathias")) {
					System.out.println("KO : Vous n'avez pas modifié correctement l'ensemble des clients dans la file : ens attendu = [mathias, loic] mais ens reçu = "+ensFA) ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		return testOK ;
	}
	
	private static boolean testSelectionnerClientSuivant() {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			String client = session.selectionnerClientSuivant() ;
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
			if (client!=null) {
				System.out.println("KO : La file était vide, il fallait renvoyer null, or on a reçu :"+client) ;
				testOK = false ;
			} else if (fileAttente.size()!=0) {
				System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait du l'etre") ;
				testOK = false ;
			} else if (ensFA.size()!=0) {
				System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'etre") ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    fileAttente.addLast("loic") ;
		    ensFA.add("loic") ;
			String client = session.selectionnerClientSuivant() ;
			if (!client.equals("loic")) {
				System.out.println("KO : client attendu = loic, mais client reçu :"+client) ;
				testOK = false ;
			} else if (fileAttente.contains("loic")) {
				System.out.println("KO : Vous avez oublié de retirer le client de la file") ;
				testOK = false ;
			} else if (fileAttente.size()!=0) {
				System.out.println("KO : file attendue : [] mais file reçue : "+fileAttente) ;
				testOK = false ;
			} else if (ensFA.contains("loic")) {
				System.out.println("KO : Vous avez oublié de retirer le client de l'ensemble des clients de la file") ;
				testOK = false ;
			} else if (ensFA.size()!=0) {
				System.out.println("KO : ensemble attendu : [] mais ensemble reçu : "+ensFA) ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		try {
			ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		    fileAttente.addLast("loic") ;
		    ensFA.add("loic") ;
		    fileAttente.addLast("mathias") ;
		    ensFA.add("mathias") ;
		    fileAttente.addLast("leo") ;
		    ensFA.add("leo") ;
		    ArrayDeque<String> fileAttendue = new ArrayDeque<String>() ;
		    fileAttendue.addLast("mathias") ;
		    fileAttendue.addLast("leo") ;
		    HashSet<String> ensAttendu = new HashSet<String>() ;
		    ensAttendu.add("mathias") ;
		    ensAttendu.add("leo") ;
		    String file = fileAttente.toString() ;
			String client = session.selectionnerClientSuivant() ;
			if (!client.equals("loic")) {
				System.out.println("KO : client attendu = loic, mais client reçu :"+client+" or la file était "+file) ;
				testOK = false ;
			} else if (fileAttente.contains("loic")) {
				System.out.println("KO : Vous avez oublié de retirer le client de la file") ;
				testOK = false ;
			} else if (!fileAttente.toString().equals(fileAttendue.toString())) {
				System.out.println("KO : file attendue : "+fileAttendue+" mais file reçue : "+fileAttente) ;
				testOK = false ;
			} else if (ensFA.contains("loic")) {
				System.out.println("KO : Vous avez oublié de retirer le client de l'ensemble des clients de la file") ;
				testOK = false ;
			} else if (!ensFA.equals(ensAttendu)) {
				System.out.println("KO : ensemble attendu : "+ensAttendu+" mais ensemble reçu : "+ensFA) ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		return testOK ;
	}
	
	public static boolean testerPasserNouvelleCommande() throws IllegalAccessException {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			session.passerNouvelleCommande(null,1) ;
			System.out.println("KO : Il fallait une exception car le client est null") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerNouvelleCommande(new String(),1) ;
			System.out.println("KO : Il fallait une exception car le client est vide") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerNouvelleCommande(new String("loic"),-1) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est <=0 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 4 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerNouvelleCommande(new String("loic"),0) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est <=0 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 5 : ") ;
		session = new SessionDeVente(5) ;
		ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
	    HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
	    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
		ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
		Commande commande = new Commande("loic",1) ;
		listeCommandes.add(commande) ;
		mapClientCommande.put("loic",commande) ;
		ArrayList<Commande> listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		HashMap<String, Commande> mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
		try {
			session.passerNouvelleCommande(new String("loic"),1) ;
			System.out.println("KO : Il fallait une IllegalStateException car le client avait déjà passé une commande") ;
			testOK = false ;
		} catch(IllegalStateException e) {
			int nca = nbCom.getInt(session);
			if (fileAttente.size()!=0) {
				System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
				testOK = false ;
			} else if (ensFA.size()!=0) {
				System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
				testOK = false ;
			} else if (!listeCommandes.equals(listeAttendue)) {
				System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
				testOK = false ;
			} else if (!mapClientCommande.equals(mapAttendu)) {
				System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
				testOK = false ;
			} else if (nca != 5) {
				System.out.println("KO : Le nombre de casier restant a ete modifié alors qu'il n'aurait pas du l'être") ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalStateException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 6 : ") ;
		session = new SessionDeVente(5) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",2) ;
			mapClientCommande.put("mathias",commande);
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		    nbCom.setInt(session,0);
			if (session.passerNouvelleCommande(new String("loic"),1)) {
				System.out.println("KO : Votre méthode renvoie true mais il n'y avait plus de casier disponible") ;
				testOK = false ;
			} else {
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (nca != 0) {
					System.out.println("KO : Le nombre de casier restant a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 7 : ") ;
		session = new SessionDeVente(1) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
			mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",2) ;
			mapClientCommande.put("mathias",commande);
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			if (session.passerNouvelleCommande(new String("loic"),2)) {
				System.out.println("KO : Votre méthode renvoie true mais il n'y a plus assez de casier disponible") ;
				testOK = false ;
			} else {
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifié alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (nca != 1) {
					System.out.println("KO : Le nombre de casier restant a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 8 : ") ;
		session = new SessionDeVente(SessionDeVente.MAX_CASIERS_CLIENT+3) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
			mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",2) ;
			mapClientCommande.put("mathias",commande);
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			if (session.passerNouvelleCommande(new String("loic"),SessionDeVente.MAX_CASIERS_CLIENT+1)) {
				System.out.println("KO : Votre méthode renvoie true mais le client a demande plus de casier que le max autorise") ;
				testOK = false ;
			} else {
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (nca != SessionDeVente.MAX_CASIERS_CLIENT+3) {
					System.out.println("KO : Le nombre de casier restant a ete modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 9 : ") ;
		int nbCasiers = SessionDeVente.MAX_CASIERS_CLIENT+3 ;
		session = new SessionDeVente(nbCasiers) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			commande = new Commande("loic",2) ;
			mapAttendu.put("loic",commande) ;
			listeAttendue.add(commande) ;
			if (!session.passerNouvelleCommande(new String("loic"), 2)) {
				System.out.println("KO : Votre méthode renvoie false mais le client pouvais commander") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifié alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients dans la liste a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes attendue : "+listeAttendue+" mais liste reçue : "+listeCommandes) ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : map commande attendu : "+mapAttendu+" mais map reçu : " +mapClientCommande) ;
					testOK = false ;
				} else if (nca!=nbCasiers-2) {
					System.out.println("KO : le nombre de casiers restants n'est pas bon : attendu :"+(nbCasiers-2)+" mais rçcu : "+nca) ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 10 : ") ;
		nbCasiers = SessionDeVente.MAX_CASIERS_CLIENT+5 ;
		session = new SessionDeVente(nbCasiers) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",1) ;
			mapClientCommande.put("mathias",commande) ;
			listeCommandes.add(commande) ;
			commande = new Commande("chloe",3) ;
			mapClientCommande.put("cloe",commande) ;
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			commande = new Commande("loic",2) ;
			mapAttendu.put("loic",commande) ;
			listeAttendue.add(commande) ;
			if (!session.passerNouvelleCommande(new String("loic"), 2)) {
				System.out.println("KO : Votre méthode renvoie false mais le client pouvais commander") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients dans la liste a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes attendue : "+listeAttendue+" mais liste reçue : "+listeCommandes) ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : map commande attendu : "+mapAttendu+" mais map reçu : " +mapClientCommande) ;
					testOK = false ;
				} else if (nca!=nbCasiers-2) {
					System.out.println("KO : le nombre de casiers restants n'est pas bon : attendu :"+(nbCasiers-2)+" mais reçu : "+nca) ;
					testOK = false ;
				} else  {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		return testOK ;
	}
	
	public static boolean testerModifierCommande() throws IllegalAccessException {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			session.modifierCommande(null,1) ;
			System.out.println("KO : Il fallait une exception car le client est null") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.modifierCommande(new String(),1) ;
			System.out.println("KO : Il fallait une exception car le client est vide") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		ArrayDeque<String> fileAttente = (ArrayDeque<String>) fileA.get(session);
		HashSet<String> ensFA = (HashSet<String>) ensClM.get(session); 
		HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
		ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
		Commande commande = new Commande("loic",2) ;
		mapClientCommande.put("loic",commande) ;
		listeCommandes.add(commande) ;
		HashMap<String, Commande> mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
		ArrayList<Commande> listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;

		try {
			session.modifierCommande(new String("loic"),-1) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est <=0 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 4 : ") ;
		session = new SessionDeVente(5) ;
		fileAttente = (ArrayDeque<String>) fileA.get(session);
		ensFA = (HashSet<String>) ensClM.get(session); 
		mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
		listeCommandes = (ArrayList<Commande>) listeC.get(session);
		commande = new Commande("loic",2) ;
		mapClientCommande.put("loic",commande) ;
		listeCommandes.add(commande) ;
	    mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
		listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		try {
			session.modifierCommande(new String("loic"),0) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est <=0 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 5 : ") ;
		session = new SessionDeVente(5) ;
		fileAttente = (ArrayDeque<String>) fileA.get(session);
	    ensFA = (HashSet<String>) ensClM.get(session); 
	    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
		listeCommandes = (ArrayList<Commande>) listeC.get(session);
		commande = new Commande("mathias",2) ;
		mapClientCommande.put("mathias",commande);
		listeCommandes.add(commande) ;
		listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
		try {
			session.modifierCommande(new String("loic"),1) ;
			System.out.println("KO : Il fallait une IllegalStateException car le client n'a pas encore passé de commande") ;
			testOK = false ;
		} catch(IllegalStateException e) {
			int nca = nbCom.getInt(session) ;
			if (fileAttente.size()!=0) {
				System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
				testOK = false ;
			} else if (ensFA.size()!=0) {
				System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
				testOK = false ;
			} else if (!listeCommandes.equals(listeAttendue)) {
				System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
				testOK = false ;
			} else if (!mapClientCommande.equals(mapAttendu)) {
				System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
				testOK = false ;
			} else if (nca != 5) {
				System.out.println("KO : Le nombre de casier restant a ete modifié alors qu'il n'aurait pas du l'être") ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalStateException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 6 : ") ;
		session = new SessionDeVente(5) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("loic",2) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande);
		    nbCom.setInt(session,0);
		    listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		    mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			if (session.modifierCommande(new String("loic"),1)) {
				System.out.println("KO : Votre méthode renvoie true mais il n'y avait plus de casier disponible") ;
				testOK = false ;
			} else {
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (nca != 0) {
					System.out.println("KO : Le nombre de casier restant a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 7 : ") ;
		int nbCasiers = SessionDeVente.MAX_CASIERS_CLIENT+5 ;
		session = new SessionDeVente(nbCasiers) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("loic",SessionDeVente.MAX_CASIERS_CLIENT-1) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande) ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			if (session.modifierCommande(new String("loic"),3)) {
				System.out.println("KO : Votre méthode renvoie true mais il n'y a plus assez de casier disponible") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (nca!=SessionDeVente.MAX_CASIERS_CLIENT+5) {
					System.out.println("KO : Le nombre de casier disponible a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 8 : ") ;
		session = new SessionDeVente(SessionDeVente.MAX_CASIERS_CLIENT+3) ;
		try {
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("loic",1) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande) ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			if (session.modifierCommande(new String("loic"),SessionDeVente.MAX_CASIERS_CLIENT+1)) {
				System.out.println("KO : Votre méthode renvoie true mais le client a demande plus de casier que le max autorise") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session);
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : L'ensemble des commandes a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (nca!=SessionDeVente.MAX_CASIERS_CLIENT+3) {
					System.out.println("KO : Le nombre de casier disponible a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 9 : ") ;
		nbCasiers = SessionDeVente.MAX_CASIERS_CLIENT+2 ;
		session = new SessionDeVente(nbCasiers) ;
		try {
			
			fileAttente = (ArrayDeque<String>) fileA.get(session);
		    ensFA = (HashSet<String>) ensClM.get(session); 
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			commande = new Commande("loic",1) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande) ;
			Commande commande2 = new Commande("loic",3) ;
			listeAttendue.add(commande2) ;
			mapAttendu.put("loic",commande2) ;
			if (!session.modifierCommande(new String("loic"),2)) {
				System.out.println("KO : Votre méthode renvoie false mais le client pouvait modifier sa commande") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a été modifiée alors qu'elle n'aurait pas du l'être") ;
					testOK = false ;
				} else if (ensFA.size()!=0) {
					System.out.println("KO : L'ensemble des clients dans la liste a été modifié alors qu'il n'aurait pas du l'être") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes attendue : "+listeAttendue+" mais liste reçue : "+listeCommandes) ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : map commande attendu : "+mapAttendu+" mais map reçu : " +mapClientCommande) ;
					testOK = false ;
				} else if (nca!=SessionDeVente.MAX_CASIERS_CLIENT) {
					System.out.println("KO : nombre de casiers disponibles attendu : "+SessionDeVente.MAX_CASIERS_CLIENT+" mais reçu :"+nca) ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		return testOK ;
	}
	
}