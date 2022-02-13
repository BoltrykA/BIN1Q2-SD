import java.util.Arrays;

// implementation de l'interface File via une table circulaire

/**
 * @author Alicia Boltryk
 *
 */

public class FileImplViaTable<E> implements File<E>{

	private Object[] table;  // ne modifiez pas cet identifiant, la classe test l'utilise					
	private int indiceTete;  // ne modifiez pas cet identifiant, la classe test l'utilise			
	private int taille;		// ne modifiez pas cet identifiant, la classe test l'utilise	
	// N'ajoutez pas d'autres attributs, la classe test risquerait de ne pas fonctionner
	

	public FileImplViaTable(){
		table = new Object[4];
		taille = 0;
		indiceTete = 0;
	}
	

	public boolean estVide(){
		return taille == 0;
	}


	public int taille(){
		return taille;
	}

	public E premier() throws FileVideException{
		if (taille == 0) throw new FileVideException();
		return (E) table[indiceTete];
	}


	public E defile() throws FileVideException{
		if (taille == 0) throw new FileVideException();
		E res = (E) table[indiceTete];
		taille--;
		indiceTete = (indiceTete + 1) % table.length;
		return res;
	}


	public void enfile(E element){
		if (table.length == taille){
			Object[] tTemp = new Object[table.length*2];
			int indexNew = 0;
			for (int i = indiceTete; i < indiceTete + taille; i++) {
				int index = i % table.length;
				tTemp[indexNew++] = table[index];
			}
			table = tTemp;
			indiceTete = 0;
		}
		table[(taille+indiceTete)%table.length] = element;
		taille++;
	}

} 
