public class Exposant {
    private String nom;
    private String email;
    private String numTel;

    public Exposant(String nom, String email, String numTel){
        this.nom = nom;
        this.email = email;
        this.numTel = numTel;
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
                '}';
    }
}
