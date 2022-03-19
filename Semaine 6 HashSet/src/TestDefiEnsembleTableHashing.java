
public class TestDefiEnsembleTableHashing {
	public static void main(String[] args) {	
		System.out.println("***************************");
		System.out.println("Programme Test pour le defi");
		System.out.println("***************************");
		Ensemble<Integer> e;
		e = new EnsembleTableHashing<Integer>(4,0.75);
		e.ajouter(new Integer(5));
		System.out.println(e.toString());
		e.ajouter(new Integer(7));
		System.out.println(e.toString());
		e.ajouter(new Integer(9));
		System.out.println(e.toString());
		e.ajouter(new Integer(1));
		System.out.println(e.toString());
		e.ajouter(new Integer(8));
		System.out.println(e.toString());
	}
}
