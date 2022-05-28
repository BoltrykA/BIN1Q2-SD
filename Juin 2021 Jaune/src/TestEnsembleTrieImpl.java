import java.util.Scanner;


public class TestEnsembleTrieImpl {
	
	private static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		
		System.out.println("*******************************");
		System.out.println("Programme Test EnsembleTrieImpl");
		System.out.println("*******************************");
		int choix= 0;
	
		do {
			System.out.println();
			System.out.println("1 ->  Tester la methode elementLePlusGrand()");
			System.out.println("2 ->  Tester la methode ajoute()");
			System.out.println("3 ->  Tester la methode elementSuivant()");
		
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix=scanner.nextInt();
			switch (choix) {
			
			case 1:
				testElementLePlusGrand();
				break;
			case 2:
				testAjoute();
				break;
			case 3:
				testElementSuivant();
				break;
		
			default:
				break;
			}
		} while (choix >= 1 && choix <= 3);

	}


	private static void testElementLePlusGrand() {
		System.out.println();
		System.out.println("Test elementLePlusGrand() avec l'ensemble de l'enonce");
		EnsembleTrieImpl<String> e = new EnsembleTrieImpl<String>("lea","anouk","zoe","sam","hugo","tim","marie");
		String nomTrouve = e.elementLePlusGrand();
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null pour l'ensemble de l'enonce");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals("zoe")){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Le test avec l'ensemble de l'enonce a reussi !");
		
		System.out.println();
		System.out.println("Test elementLePlusGrand() avec l'ensemble vide");
		EnsembleTrieImpl<String> eVide = new EnsembleTrieImpl<String>();
		nomTrouve = eVide.elementLePlusGrand();
		if(nomTrouve!=null){
			System.out.println("Attention, votre methode n'a pas renvoye null pour l'ensemble vide");
			System.out.println("Revoyez votre methode !");		
			return;
		}
		
		System.out.println("Le test avec l'ensemble vide a reussi !");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi !");
		
			
	}

	private static void testAjoute() {
		System.out.println();
		System.out.println("Test ajoute() en partant de l'arbre vide et en construisant l'ensemble de l'enonce");
		System.out.println("Les ajouts se feront selon l'ordre : lea anouk zoe sam hugo tim marie");
		EnsembleTrieImpl<String> e = new EnsembleTrieImpl<String>();
		
		e.ajoute("lea");
		if(!e.toString().equals("[ lea ]")){
			System.out.println("Attention apres ajout de lea, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [ lea ]");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(e.taille()!=1){
			System.out.println("Attention apres ajout de lea, l'arbre que vous avez obtenu est ok et contient lea");	
			System.out.println("Mais la taille n'est pas correcte : "+ e.taille());
			System.out.println("Avez-vous pense a mettre a jour l'attribut taille ?");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		e.ajoute("anouk");
		if(!e.toString().equals("[  [ anouk ] lea [ ]  ]")){
			System.out.println("Attention apres ajout de anouk, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [  [ anouk ] lea [ ]  ]");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(e.taille()!=2){
			System.out.println("Attention apres ajout de anouk, l'arbre que vous avez obtenu est ok et contient anouk lea");	
			System.out.println("Mais la taille n'est pas correcte : "+ e.taille());
			System.out.println("Avez-vous pense a mettre a jour l'attribut taille ?");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		e.ajoute("zoe");
		if(!e.toString().equals("[  [ anouk ] lea [ zoe ]  ]")){
			System.out.println("Attention apres ajout de zoe, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [  [ anouk ] lea [ zoe ]  ]");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(e.taille()!=3){
			System.out.println("Attention apres ajout de anouk, l'arbre que vous avez obtenu est ok et contient anouk lea zoe");	
			System.out.println("Mais la taille n'est pas correcte : "+ e.taille());
			System.out.println("Avez-vous pense a mettre a jour l'attribut taille ?");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		e.ajoute("sam");
		if(!e.toString().equals("[  [ anouk ] lea [  [ sam ] zoe [ ]  ]  ]")){
			System.out.println("Attention apres ajout de sam, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [  [ anouk ] lea [  [ sam ] zoe [ ]  ]  ] ");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(e.taille()!=4){
			System.out.println("Attention apres ajout de sam, l'arbre que vous avez obtenu est ok et contient anouk lea sam zoe");	
			System.out.println("Mais la taille n'est pas correcte : "+ e.taille());
			System.out.println("Avez-vous pense a mettre a jour l'attribut taille ?");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		e.ajoute("hugo");
		if(!e.toString().equals("[  [  [ ] anouk [ hugo ]  ] lea [  [ sam ] zoe [ ]  ]  ]")){
			System.out.println("Attention apres ajout de hugo, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [  [  [ ] anouk [ hugo ]  ] lea [  [ sam ] zoe [ ]  ]  ]");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(e.taille()!=5){
			System.out.println("Attention apres ajout de hugo, l'arbre que vous avez obtenu est ok et contient anouk hugo lea sam zoe");	
			System.out.println("Mais la taille n'est pas correcte : "+ e.taille());
			System.out.println("Avez-vous pense a mettre a jour l'attribut taille ?");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		e.ajoute("tim");
		if(!e.toString().equals("[  [  [ ] anouk [ hugo ]  ] lea [  [  [ ] sam [ tim ]  ] zoe [ ]  ]  ]")){
			System.out.println("Attention apres ajout de tim, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [  [  [ ] anouk [ hugo ]  ] lea [  [  [ ] sam [ tim ]  ] zoe [ ]  ]  ]");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		e.ajoute("marie");
		if(!e.toString().equals("[  [  [ ] anouk [ hugo ]  ] lea [  [  [ marie ] sam [ tim ]  ] zoe [ ]  ]  ]")){
			System.out.println("Attention apres ajout de marie, l'arbre que vous avez obtenu est ");
			System.out.println(e.toString());
			System.out.println("Il devrait etre [  [  [ ] anouk [ hugo ]  ] lea [  [  [ marie ] sam [ tim ]  ] zoe [ ]  ]  ]");
			System.out.println();
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println(e.toString());
		
		System.out.println();
		System.out.println("Tous les tests ont reussi !");
		
	}

	
	private static void testElementSuivant() {
		System.out.println();
		System.out.println("Test elementSuivant() avec l'ensemble de l'enonce");
		EnsembleTrieImpl<String> e = new EnsembleTrieImpl<String>("lea","anouk","zoe","sam","hugo","tim","marie");
		String nom, nomSuivant, nomTrouve;
		nom = "hugo";
		nomSuivant = "lea";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null");
			System.out.println("L'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals(nomSuivant)){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}	
		
		nom = "lea";
		nomSuivant = "marie";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null");
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals(nomSuivant)){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}	
		
		nom = "marie";
		nomSuivant = "sam";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null");
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals(nomSuivant)){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}	
		
		nom = "sam";
		nomSuivant = "tim";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null");
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals(nomSuivant)){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}	
		
		nom = "tim";
		nomSuivant = "zoe";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null");
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals(nomSuivant)){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		
		nom = "anouk";
		nomSuivant = "hugo";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve==null){
			System.out.println("Attention, votre methode a renvoye null");
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		if(!nomTrouve.equals(nomSuivant)){
			System.out.println("Attention, votre methode a renvoye "+ nomTrouve);
			System.out.println("l'element suivant de "+ nom + " est "+ nomSuivant);
			System.out.println("Revoyez votre methode !");		
			return;
		}	
		
		nom = "zoe";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve!=null){	
			System.out.println("zoe n'a pas d'element suivant");
			System.out.println("Attention, votre methode n'a pas renvoye null");
			System.out.println("Votre methode a renvoye "+ nomTrouve);
			System.out.println("Revoyez votre methode !");		
			return;
		}
			
		nom = "bob";
		nomTrouve = e.elementSuivant(nom);
		if(nomTrouve!=null){
			System.out.println("bob n'a pas d'element suivant, car il n'est pas dans l'ensemble");
			System.out.println("Attention, votre methode n'a pas renvoye null");
			System.out.println("Votre methode a renvoye "+ nomTrouve);
			System.out.println("Revoyez votre methode !");		
			return;
		}
		System.out.println("Tous les tests avec l'ensemble de l'enonce ont reussi !");
		
		System.out.println();
		
		
		System.out.println("Test elementSuivant() avec l'ensemble vide");
		EnsembleTrieImpl<String> eVide = new EnsembleTrieImpl<String>();
		nom = "lea";
		if(eVide.elementSuivant(nom)!=null){	
			System.out.println("anouk n'appartient pas a l'ensemble et n'a donc pas d elementSuivant");
			System.out.println("Attention, votre methode n'a pas renvoye null");
			System.out.println("Votre methode a renvoye "+ nomTrouve);
			System.out.println("Revoyez votre methode !");
			return;
		}
		
		System.out.println("Le test avec l'ensemble vide a reussi !");
		
		System.out.println();
		System.out.println("Tous les tests ont reussi !");
	}

}

