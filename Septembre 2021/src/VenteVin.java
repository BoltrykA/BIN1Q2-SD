import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class VenteVin {
	
	private ArrayDeque<String> [] tableFilesDAttente;
	private HashSet<String> ensembleClientsEnAttente; //presents dans une des files d'attente
	private HashMap<String, Commande> mapClientCommande;	
	private ArrayList<Commande> listeCommandes;
	private int nombreBouteillesRestantes;
		
	
	/**
	 * debute la vente
	 * @param nombreBouteillesMisesEnVente le nombre de bouteilles mises en vente
	 * @throws IllegalArgumentException s'il n'y a pas au moins une bouteille a vendre 
	 */
	public VenteVin(int nombreBouteillesMisesEnVente) {
		if(nombreBouteillesMisesEnVente<=0)
			throw new IllegalArgumentException();
		this.nombreBouteillesRestantes = nombreBouteillesMisesEnVente;
		tableFilesDAttente = new ArrayDeque[4];
		for (int i = 0; i < tableFilesDAttente.length; i++) {
			tableFilesDAttente[i]=new ArrayDeque<String>();
		}
		ensembleClientsEnAttente = new HashSet<String>();
		mapClientCommande = new HashMap<String, Commande>();
		listeCommandes = new ArrayList<Commande>();		
	}
	
	public int getNombreBouteillesRestantes() {
		return nombreBouteillesRestantes;
	}
	
	/**
	 * ajoute le client dans une des files d'attente en fonction du nombre de bouteilles deja commandes
	 * 0 bouteille --> file 0, 1 bouteille --> file 1, 2 bouteilles --> file 2, 3 bouteilles ou plus --> file 3
	 * le client ne peut pas deja etre dans une de files
	 * @param client le client a ajouter
	 * @return true si l'ajout a pu se faire, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 */
	public boolean mettreEnAttente(String client){
		//TODO
		return false;
	}
		
	/**
	 * selectionne (et retire) le client le plus prioritaire
	 * le client le plus prioritaire est celui en tete de file qui a commande le moins de bouteilles
	 * @return le client selectionne ou null si les toutes les files sont vides
	 */
	public String selectionnerClientSuivant(){
		//TODO
		return null;
	}
	
	/**
	 * cree une commande ou modifie une commande existante si le client a deja passe commande
	 * le nombre de bouteilles restantes doit etre non nul
	 * @param client le client qui fait la demande 
	 * @return true si la demande a pu etre faite, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 */
	public boolean demanderUneBouteille(String client){
		//TODO
		return false;
	}	
		
	
	
	/**
	 * supprime une commande existante
	 * une commande doit exister pour ce client
	 * les bouteilles sont remises en vente
	 * @param client le client qui veut supprimer sa commande
	 * @return true si une commande a pu etre supprimee, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide 
	 */
	public boolean supprimerCommande(String client){
		//TODO
		return false;	
	}
		
	public String toString(){
		// cette methode ne sera pas evaluee
		// elle peut-etre interessante a appeler en cas de bug
		// n'hesitez pas a la completer
		return "nombre bouteilles restantes "+ nombreBouteillesRestantes+ 
				"\nfile clients"+ Arrays.toString(tableFilesDAttente)+
				"\nensemble des clients en attente"+ensembleClientsEnAttente.toString()+
				"\nmap client-commande"+mapClientCommande.toString()+
				"\nliste des commandes"+ listeCommandes.toString();
	}

}

		
	
	
	
	
	

