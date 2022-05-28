import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EuroMatch {
	
	private ArrayDeque<String> fileAttente;
	private HashSet<String> ensembleClientsActuellementDansFile;
	private HashMap<String, Reservation> mapClientReservation;	
	private ArrayList<Reservation> listeReservations;
	private int nombrePlacesRestantes;
	private final static int MAX_PLACES_CLIENT = 5;
	
	
	/**
	 * debute les reservations des places du prochain match
	 * @param nombrePlaces le nombre de places total disponibles pour le prochain match
	 * @throws IllegalArgumentException s'il n'y a pas au moins une place 
	 */
	public EuroMatch(int nombrePlaces) {
		if(nombrePlaces<=0)
			throw new IllegalArgumentException();
		this.nombrePlacesRestantes = nombrePlaces;
		fileAttente = new ArrayDeque<String>();
		ensembleClientsActuellementDansFile = new HashSet<String>();
		mapClientReservation = new HashMap<String, Reservation>();
		listeReservations = new ArrayList<Reservation>();
	}

	
	public int getNombrePlacesRestantes() {
		return nombrePlacesRestantes;
	}
	
	/**
	 * ajoute, si possible, le client dans la file d'attente
	 * le client ne peut pas deja y etre
	 * si client a deja une reservation pour ce match, le max de places autorise n'est pas deja atteint
	 * il reste des places a reserver
	 * @param client le client a ajouter
	 * @return true si l'ajout a pu se faire, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 */
	public boolean placerDansFileAttente(String client){
		//TODO
		return false;
	}
		
	/**
	 * retire de la file d'attente le client de tete
	 * @return le client de tete ou null si la file est vide
	 */
	public String selectionnerClientSuivant(){
		//TODO
		return null;
	}
	
	/**
	 * ajoute, si possible, une nouvelle reservation  
	 * le nombre de places restantes doit etre suffisant pour satisfaire completement la reservation
	 * (il n'y a pas de reservation partielle)
	 * le nombre de places demandees ne peut depasser le max autorise
	 * @param client le client qui fait la demande
	 * @param nombrePlacesDemandees le nombre de places demandees
	 * @return true si la reservation a pu etre faite, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 *  	ou si le nombre de places demandees est <= 0
	 * @throws IllegalStateException si le client a deja fait une reservation pour ce match  
	 */
	public boolean passerNouvelleReservation(String client, int nombrePlacesDemandees){
		//TODO
		return false;
	}	
	
	
	/**
	 * modifie, si possible, une reservation existante
	 * le nombre de places restantes doit etre suffisant
	 * (il n'y a pas de reservation partielle)
	 * le nombre de places de la reservation apres ajout du nombre demande en plus ne peut depasser le max autorise
	 * @param client le client qui veut modifier sa reservation
	 * @param nombrePlacesDemandeesEnPlus le nombre de places qui va s'ajouter au nombre de places deja reservees
	 * @return true si la reservation a pu etre modifiee, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 *  	ou si le nombre de places demandees est <= 0
	 * @throws IllegalStateException si le client n'a pas encore fait de reservation pour ce match
	 */
	public boolean modifierReservation(String client,int nombrePlacesDemandeesEnPlus){
		//TODO
		return false;
	}	

	
	public String toString(){
		// cette methode ne sera pas evaluee
		// elle peut-etre interessante a appeler en cas de bug
		// n'hesitez pas a la completer
		return "le nombre de places restantes : "+ nombrePlacesRestantes 
				+ "\nla file d'attente : "+ fileAttente +  "\nles reservations " + listeReservations;
	}

}

		
	
	
	
	
	

