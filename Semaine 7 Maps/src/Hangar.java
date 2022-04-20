import java.util.Objects;

public class Hangar {
	
	private int numeroHangar;
	private Societe societe;
	
	public Hangar(int numeroHangar) {
		this.numeroHangar = numeroHangar;
	}

	public int getNumeroHangar() {
		return numeroHangar;
	}
	
	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	@Override
	public String toString() {
		return "Hangar [numeroHangar=" + numeroHangar + ", societe=" + societe + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hangar hangar = (Hangar) o;
		return numeroHangar == hangar.numeroHangar;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroHangar);
	}
}
