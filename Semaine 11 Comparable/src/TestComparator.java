import java.util.Comparator;
import java.util.TreeSet;

public class TestComparator {	
	public static void main(String[] args) {		
		
//		Comparator<String> comparateur = new Comparator<String>(){
//			public int compare(String nom1, String nom2) {
//				if(nom1.length()<nom2.length())
//				return -1;
//				if(nom1.length()>nom2.length())
//				return 1;
//				return nom1.compareTo(nom2);
//			}
//		};
		
		Comparator<String> comparateur = new AutreComparateur();
		TreeSet<String> t = new TreeSet<String>(comparateur);
		t.add("amandine");
		t.add("tom");
		t.add("marie");
		t.add("lea");
		t.add("marie");
		t.add("sam");
//		System.out.println(t);
		for (String nom : t) {
			System.out.println(nom);
		}	
	}
}


