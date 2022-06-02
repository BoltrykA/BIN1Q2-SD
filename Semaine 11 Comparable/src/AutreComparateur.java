import java.util.Comparator;

public class AutreComparateur implements Comparator<String>{
	public int compare(String nom1, String nom2) {
		if(nom1.length()<nom2.length())
		return -1;
		if(nom1.length()>nom2.length())
		return 1;
		return nom1.compareTo(nom2);
	}
}

