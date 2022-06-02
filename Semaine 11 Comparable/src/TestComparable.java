import java.util.TreeSet;

public class TestComparable {
	public static void main(String[] args) {
		TreeSet<String> t = new TreeSet<String>();
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
