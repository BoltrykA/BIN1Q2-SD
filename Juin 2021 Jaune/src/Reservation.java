
public class Reservation {
	
	private String client;
	private int nombrePlacesReservees;

	/**
	 * 
	 * @param client
	 * @param nombrePlacesDemandees
	 */
	public Reservation(String client, int nombrePlacesReservees) {
		super();
		this.client = client;
		setNombrePlacesReservees(nombrePlacesReservees);
	}

	public String getClient() {
		return client;
	}

	public int getNombrePlacesReservees() {
		return nombrePlacesReservees;
	}

	public void setNombrePlacesReservees(int nombrePlacesReservees) {
		if(nombrePlacesReservees<0)
			throw new IllegalArgumentException();
		this.nombrePlacesReservees = nombrePlacesReservees;
	}

	@Override
	public String toString() {
		return "Reservation [client=" + client + ", nombrePlacesReservees="
				+ nombrePlacesReservees + "]";
	}
	
	
	

	
	
	
}
