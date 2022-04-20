public class Emplacement {
    private int numero;
    private Exposant exposant;

    public Emplacement(int numero, Exposant exposant){
        this.numero = numero;
        this.exposant = exposant;
    }

    public Emplacement(int numero){
        this(numero,null);
    }

    public int getNumero() {
        return numero;
    }

    public Exposant getExposant() {
        return exposant;
    }

    public void setExposant(Exposant exposant) {
        this.exposant = exposant;
    }

    @Override
    public String toString() {
        return "Emplacement{" +
                "numero=" + numero +
                ", exposant=" + exposant +
                '}';
    }
}
