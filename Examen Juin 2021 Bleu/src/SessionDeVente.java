import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SessionDeVente {
	
	private ArrayDeque<String> fileAttente;
	private HashSet<String> ensembleClientsActuellementDansFile;
	private HashMap<String, Commande> mapClientCommande;	
	private ArrayList<Commande> listeCommandes;
	private int nombreCasiersRestants;
	public final static int MAX_CASIERS_CLIENT = 3;
	
	
	/**
	 * debute une session de vente
	 * @param nombreCasiersMisEnVente le nombre de casiers mis en vente
	 * @throws IllegalArgumentException s'il n'y a pas au moins un casier a vendre 
	 */
	public SessionDeVente(int nombreCasiersMisEnVente) {
		if(nombreCasiersMisEnVente<=0)
			throw new IllegalArgumentException();
		this.nombreCasiersRestants = nombreCasiersMisEnVente;
		fileAttente = new ArrayDeque<String>();
		ensembleClientsActuellementDansFile = new HashSet<String>();
		mapClientCommande = new HashMap<String, Commande>();
		listeCommandes = new ArrayList<Commande>();
	}

	
	public int getNombreCasiersRestants() {
		return nombreCasiersRestants;
	}
	
	/**
	 * ajoute, si possible, le client dans la file d'attente
	 * le client ne peut pas deja y etre
	 * si client a deja une commande lors de cette session de vente, le max de casiers autorise n'est pas deja atteint
	 * s'il reste encore des casiers a vendre
	 * @param client le client a ajouter
	 * @return true si l'ajout a pu se faire, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 */
	public boolean placerDansFileAttente(String client){
		if (client == null || client.equals("")) throw new IllegalArgumentException();
		if (fileAttente.contains(client) || nombreCasiersRestants == 0) return false;
		if (mapClientCommande.containsKey(client)){
			Commande commande = mapClientCommande.get(client);
			if (commande.getNombreCasiersDemandes() == MAX_CASIERS_CLIENT) return false;
		}
		fileAttente.add(client);
		ensembleClientsActuellementDansFile.add(client);
		return true;
	}
		
	/**
	 * retire de la file d'attente le client de tete
	 * @return le client de tete ou null si la file est vide
	 */
	public String selectionnerClientSuivant(){
		if (fileAttente.isEmpty()) return null;
		String client = fileAttente.removeFirst();
		ensembleClientsActuellementDansFile.remove(client);
		return client;
	}
	
	/**
	 * ajoute, si possible, une nouvelle commande  
	 * le nombre de casiers restants doit etre suffisant pour satisfaire completement la commande
	 * (il n'y a pas de commande partielle)
	 * le nombre de casiers demandes ne peut depasser le max autorise
	 * @param client le client qui fait la demande
	 * @param nombreCasiersDemandes le nombre de casiers demandes
	 * @return true si la commande a pu etre faite, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 *  	ou si le nombre de casiers demandés est <=0
	 * @throws IllegalStateException si le client a deja fait une commande  
	 */
	public boolean passerNouvelleCommande(String client, int nombreCasiersDemandes){
		if (client == null || client.equals("") || nombreCasiersDemandes <= 0) throw new IllegalArgumentException();
		if (mapClientCommande.containsKey(client)) throw new IllegalStateException();
		if (nombreCasiersRestants == 0 || nombreCasiersDemandes > 3 || nombreCasiersDemandes > nombreCasiersRestants) return false;
		Commande commande = new Commande(client, nombreCasiersDemandes);
		mapClientCommande.put(client, commande);
		listeCommandes.add(commande);
		nombreCasiersRestants -= nombreCasiersDemandes;
		return true;
	}	
		
	
	/**
	 * modifie, si possible, une commande existante
	 * le nombre de casiers restants doit etre suffisant
	 * (il n'y a pas de commande partielle)
	 * le nombre total de casiers apres ajout de ce nombre de casiers supplementaires ne peut depasser le max autorise
	 * @param client le client qui veut modifier sa commande
	 * @param nombreCasiersDemandesEnPlus le nombre de casiers a ajouter au nombre de casiers deja commande
	 * @return true si la commande a pu etre modifiee, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 *  	ou si le nombre de casiers demandes est <= 0
	 * @throws IllegalStateException si le client n'a pas encore fait de commande lors de cette session de commande
	 */
	public boolean modifierCommande(String client,int nombreCasiersDemandesEnPlus){
		if (client == null || client.equals("") || nombreCasiersDemandesEnPlus <= 0) throw new IllegalArgumentException();
		if (!mapClientCommande.containsKey(client)) throw new IllegalStateException();
		if (nombreCasiersRestants == 0 || nombreCasiersDemandesEnPlus > nombreCasiersRestants) return false;
		Commande commande = mapClientCommande.get(client);
		if (commande.getNombreCasiersDemandes() + nombreCasiersDemandesEnPlus > 3) return false;
		commande.setNombreCasiersDemandes(commande.getNombreCasiersDemandes()+nombreCasiersDemandesEnPlus);
		nombreCasiersRestants -= nombreCasiersDemandesEnPlus;
		return true;
	}	
	
	
	public String toString(){
		// cette methode ne sera pas evaluee
		// elle peut-etre interessante a appeler en cas de bug
		// n'hesitez pas a la completer
		return "le nombre de casiers restants : "+ nombreCasiersRestants 
				+ "\nla file d'attente : "+ fileAttente +  "\nles commandes " + listeCommandes;
	}

}

		
	
	
	
	
	

