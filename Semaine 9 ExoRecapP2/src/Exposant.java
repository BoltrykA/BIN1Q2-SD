import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Alicia Boltryk
 *
 */
public class Exposant {
    private String nom;
    private String email;
    private String numTel;
    private LinkedList<Emplacement> listeEmplacements;

    public Exposant(String nom, String email, String numTel){
        this.nom = nom;
        this.email = email;
        this.numTel = numTel;
        listeEmplacements = new LinkedList<>();
    }

    public Iterator<Emplacement> touslesEmplacements(){
        return listeEmplacements.iterator();
    }

    public boolean contient(Emplacement emplacement){
        return listeEmplacements.contains(emplacement);
    }

    public void ajouterEmplacement(Emplacement emplacement){
       listeEmplacements.add(emplacement);
    }

    public boolean supprimerEmplacement(Emplacement emplacement){
        return (listeEmplacements.remove(emplacement));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Exposant{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", numTel='" + numTel + '\'' +
                '}';
    }
}
