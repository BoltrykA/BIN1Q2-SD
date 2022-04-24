import java.util.ArrayList;

public class Emplacement {
    private int numero;
    private Exposant exposant;
    private char type;

    public Emplacement(int numero, char type, Exposant exposant){
        this.numero = numero;
        this.exposant = exposant;
        this.type = type;
    }

    public Emplacement(int numero, char type){ // pour l'initialiser sans exposant assigné
        this(numero,type, null);
    }

    public Emplacement(int numero){ // pour la phase 1
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Exposant getExposant() {
        return exposant;
    }

    public char getType() {
        return type;
    }

    public void setExposant(Exposant exposant) {
        this.exposant = exposant;
    }

    @Override
    public String toString() {
        return "Emplacement{" +
                "numero=" + numero +
                ", exposant=" + exposant +
                ", type=" + type +
                '}';
    }
}
