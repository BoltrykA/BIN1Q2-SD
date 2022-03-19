import java.util.Objects;

public class Guerrier {
	
	private int numero;
	private int pointsDeVie;
	
	public Guerrier(int numero, int pointsDeVie) {
		this.numero = numero;
		this.pointsDeVie = pointsDeVie;
	}


	public String toString() {
		return "Guerrier [numero=" + numero + ", pointsDeVie=" + pointsDeVie
				+ "]";
	}


	public int getNumero() {
		return numero;
	}


	public int getPointsDeVie() {
		return pointsDeVie;
	}


	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Guerrier guerrier = (Guerrier) o;
		return numero == guerrier.numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}
}
