import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

public class RallyeAutomobile {

    private ListeSDImpl<String> pilotes;
    private ListeSDImpl<String> pilotesArrives;

    public RallyeAutomobile(String [] lesPilotes) {
        pilotes = new ListeSDImpl<>();
        pilotesArrives = new ListeSDImpl<>();
        for (int i = 0; i < lesPilotes.length; i++) {
            pilotes.insererEnQueue(lesPilotes[i]);
        }
    }

    public boolean contient(String pilote){
        return pilotes.contient(pilote);
    }

    public String donnerPiloteEnTete(){
        return pilotes.premier();
    }

    public boolean supprimer(String pilote){
        return pilotes.supprimer(pilote);
    }

    public boolean faireDepasser(String pilote){
        if (!contient(pilote)) return false;
        String piloteAvantLui = pilotes.donnerPrecedent(pilote);
        if (piloteAvantLui == null) return false;
        pilotes.permuter(piloteAvantLui,pilote);
        return true;
    }

    public int position(String pilote){
        if (!contient(pilote)) return -1;
        int posPilote = 1;
        String piloteConcerne = pilotes.premier();
        while (!piloteConcerne.equals(pilote)){
            piloteConcerne = pilotes.donnerSuivant(piloteConcerne);
            posPilote++;
        }
        return posPilote;
    }

    public boolean passerLigneArrivee(String pilote){
        if (!pilote.equals(pilotes.premier())) return false;
        return (supprimer(pilote) && pilotesArrives.insererEnQueue(pilote));
    }

    public String afficherPilotesEnLice() {
        if (pilotes.estVide()) return "Aucun pilote n'est en lice";
        return pilotes.teteQueue();
    }

    public String avantLui(String pilote){
        if (!contient(pilote)) return null;
        return pilotes.donnerPrecedent(pilote);
    }

    public String afficherPilotesArrives(){
        if (pilotesArrives.estVide()) return "Aucun pilote n'est arrivé";
        return pilotesArrives.teteQueue();
    }
}
