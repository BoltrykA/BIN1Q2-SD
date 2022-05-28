
public class Commande {
	
	private String client;
	private int nombreBouteillesDemandees;
	
	public Commande(String client) {
		super();
		this.client = client;
		this.nombreBouteillesDemandees = 1;
	}

	public String getClient() {
		return client;
	}

	public int getNombreBouteillesDemandees() {
		return nombreBouteillesDemandees;
	}

	public void nombreBouteillesPlusUn() {
		this.nombreBouteillesDemandees++;
	}


	@Override
	public String toString() {
		return "Commande [client = " + client + ", nombre bouteilles demandees = "
				+ nombreBouteillesDemandees + "]";
	}

		
}
