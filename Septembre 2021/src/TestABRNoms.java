import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TestABRNoms {
	
	private static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		
		System.out.println("**********************");
		System.out.println("Programme Test ABRNoms");
		System.out.println("**********************");
		int choix= 0;
	
		do {
			System.out.println();
			System.out.println("1 ->  Tester la methode nombreNomsCommencantPar()");
			System.out.println("2 ->  Tester la methode ensembleHomonymes()");
			System.out.println("3 ->  Tester la methode descendingIterator()");
		
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix=scanner.nextInt();
			switch (choix) {
			
			case 1:
				testNombreNomsCommencantPar();
				break;
			case 2:
				testEnsembleHomonymes();
				break;
			case 3:
				testDescendingIterator();
				break;
		
			default:
				break;
			}
		} while (choix >= 1 && choix <= 3);

	}

	private static ABRNoms abrEnonce(){
		ABRNoms a = new ABRNoms();
		a.insere("lea");
		a.insere("anouk");
		a.insere("laure");
		a.insere("tim");
		a.insere("leo");
		a.insere("lea");
		a.insere("leo");
		a.insere("tim");
		return a;	
	}
	
	private static ABRNoms abr7xLeo(){
		ABRNoms a = new ABRNoms();
		a.insere("leo");
		a.insere("leo");
		a.insere("leo");
		a.insere("leo");
		a.insere("leo");
		a.insere("leo");
		a.insere("leo");
		return a;	
	}
	
	

	private static void testNombreNomsCommencantPar() {
		System.out.println();
		System.out.println("Test nombreNomsCommencantPar() avec l'ABR de l'enonce");
		ABRNoms arbreEnonce = abrEnonce();
		int nbr = arbreEnonce.nombreNomsCommencantPar('a');
		if(nbr != 1){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par a");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		nbr = arbreEnonce.nombreNomsCommencantPar('l');
		if(nbr != 5){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par l");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		nbr = arbreEnonce.nombreNomsCommencantPar('t');
		if(nbr != 2){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par t");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		nbr = arbreEnonce.nombreNomsCommencantPar('b');
		if(nbr != 0){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par b");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		nbr = arbreEnonce.nombreNomsCommencantPar('z');
		if(nbr != 0){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par z");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Tous les tests avec l'ABR de l'enonce ont reussi !");
		
		
		System.out.println();
		System.out.println("Test nombreNomsCommencantPar() avec l'ABR qui contient 7 x Leo");
		ABRNoms arbre7xLeo = abr7xLeo();
		nbr = arbre7xLeo.nombreNomsCommencantPar('a');
		if(nbr != 0){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par a");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		nbr = arbre7xLeo.nombreNomsCommencantPar('l');
		if(nbr != 7){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par l");
			System.out.println("Revoyez votre methode !");		
			return;
		}	
		System.out.println("Tous les tests avec l'ABR qui contient 7 x Leo ont reussi !");
		
		System.out.println();
		System.out.println("Test nombreNomsCommencantPar() avec l'ABR vide");
		ABRNoms arbreVide = new ABRNoms();
		nbr = arbreVide.nombreNomsCommencantPar('a');
		if(nbr != 0){
			System.out.println("Attention, votre methode annonce "+ nbr + " noms qui commencent par a");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Le test avec l'ABR vide a reussi !");
		System.out.println();
		System.out.println("Tous les tests ont reussi!");
	}


	private static void testEnsembleHomonymes() {
		System.out.println();
		System.out.println("Test ensembleHomonymes() avec l'ABR de l'enonce");
		ABRNoms arbreEnonce = abrEnonce();
		HashSet<String> ensembleAttendu = new HashSet<String>();
		ensembleAttendu.add("lea");
		ensembleAttendu.add("leo");
		ensembleAttendu.add("tim");
		HashSet<String> ensembleRecu = arbreEnonce.ensembleHomonymes();
		if(!ensembleAttendu.equals(ensembleRecu)){
			System.out.println("Attention, votre methode a renvoye l'ensemble "+ensembleRecu);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Le test avec l'ABR de l'enonce a reussi !");
		
		
		System.out.println();
		System.out.println("Test ensembleHomonymes() avec l'ABR qui contient 7 x Leo");
		ABRNoms arbre7xLeo = abr7xLeo();
		ensembleAttendu = new HashSet<String>();
		ensembleAttendu.add("leo");
		ensembleRecu = arbre7xLeo.ensembleHomonymes();
		if(!ensembleAttendu.equals(ensembleRecu)){
			System.out.println("Attention, votre methode a renvoye l'ensemble "+ensembleRecu);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Le test avec l'ABR qui contient 7 x Leo a reussi !");
		
		System.out.println();
		System.out.println("Test ensembleHomonymes() avec l'ABR vide");
		ABRNoms arbreVide = new ABRNoms();
		ensembleAttendu = new HashSet<String>();
		ensembleRecu = arbreVide.ensembleHomonymes();
		if(!ensembleAttendu.equals(ensembleRecu)){
			System.out.println("Attention, votre methode a renvoye l'ensemble "+ensembleRecu);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		
		System.out.println("Le test avec l'ABR vide a reussi !");
		System.out.println();
		System.out.println("Tous les tests ont reussi!");
		
	}


	private static void testDescendingIterator() {
		System.out.println();
		System.out.println("Test descendingIterator() avec l'ABR vide");
		ABRNoms arbreVide = new ABRNoms();
		Iterator<String> it = arbreVide.descendingIterator();
		if(it.hasNext()){	
				System.out.println("Attention, il y n'a pas de suivant");
				System.out.println("votre methode hasNext() annonce que oui");
				System.out.println("Revoyez votre methode !");		
				return;
			}
		try{
			System.out.println(it.next());
			System.out.println("Attention, next() impossible, il fallait une exception de type NoSuchElementException");
			System.out.println("Revoyez votre methode !");		
			return;
		}catch (NoSuchElementException e){

		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			System.out.println("Revoyez votre methode !");		
			return;
		}

		System.out.println("Le test avec l'ABR vide a reussi !");
		System.out.println();
		
		System.out.println("Test descendingIterator() avec l'ABR qui contient 7x leo");
		ABRNoms arbre7xLeo = abr7xLeo();
		it = arbre7xLeo.descendingIterator();
		String[] solution7 = {"leo","leo","leo","leo","leo","leo","leo"};
		int i = 0;
		while(it.hasNext()){
			try{
				String suivant = it.next();
				if(!solution7[i].equals(suivant)){
					System.out.println("Attention, l'attendu suivant est "+solution7[i]);
					System.out.println("votre methode next() a renvoye "+suivant);
					System.out.println("Revoyez votre methode !");		
					return;
				}
				i++;
				System.out.println("--> "+suivant);
			}catch (NoSuchElementException e){
				System.out.println("Attention, il y avait encore un suivant");
				System.out.println("votre methode a lance une exception de type NoSuchElementException");
				System.out.println("Revoyez votre methode !");		
				return;
			}catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Attention, il y n'avait plus de suivant");
				System.out.println("votre methode hasNext() annonce que oui");
				System.out.println("Revoyez votre methode !");		
				return;
			}
		}
		try{
			System.out.println(it.next());
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			System.out.println("Revoyez votre methode !");		
			return;
		}catch (NoSuchElementException e){
			
		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Le test avec l'ABR qui contient 7 x leo a reussi!");
		
		System.out.println("Test descendingIterator() avec l'ABR de l'enonce");
		ABRNoms arbreEnonce = abrEnonce();
		it = arbreEnonce.descendingIterator();
		String[] solution = {"tim", "tim","leo","leo","lea","lea","laure","anouk"};
		i = 0;
		while(it.hasNext()){
			try{
				String suivant = it.next();
				if(!solution[i].equals(suivant)){
					System.out.println("Attention, l'attendu suivant est "+solution[i]);
					System.out.println("votre methode a renvoye "+suivant);
					System.out.println("Revoyez votre methode !");		
					return;
				}
				i++;
				System.out.println("--> "+suivant);
			}catch (NoSuchElementException e){
				System.out.println("Attention, il y avait encore un suivant");
				System.out.println("votre methode a lance une exception de type NoSuchElementException");
				System.out.println("Revoyez votre methode !");		
				return;
			}catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Attention, il y n'avait plus de suivant");
				System.out.println("votre methode hasNext() annonce que oui");
				System.out.println("Revoyez votre methode !");		
				return;
			}
		}
		try{
			System.out.println(it.next());
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			System.out.println("Revoyez votre methode !");		
			return;
		}catch (NoSuchElementException e){

		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Le test avec l'ABR de l'enonce a reussi !");
		
		System.out.println();	
		System.out.println("Tous les tests ont reussi!");
	}


	
}

