import java.util.Scanner;

public class GestionRallyeAutomobile {

    private static String[] lesPilotes;
    private static RallyeAutomobile rallye;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("********************************");
        System.out.println("GESTION RALLYE AUTOMOBILE");
        System.out.println("********************************");

        System.out.println("Entrez le nombre de pilotes :");
        int choix = scanner.nextInt();
        scanner.nextLine();

        lesPilotes = new String[choix];

        for (int i = 1; i <= lesPilotes.length; i++) {
            System.out.println("Entrez le nom du pilote " + i);
            String pilote = scanner.nextLine();
            lesPilotes[i-1] = pilote;
        }

        rallye = new RallyeAutomobile(lesPilotes);

        do {
            System.out.println("1 -> Afficher toute la course");
            System.out.println("2 -> Afficher le pilote en tête");
            System.out.println("3 -> Enregistrer un dépassement");
            System.out.println("4 -> Disqualifier un pilote");
            System.out.println("5 -> Donner la position d’un pilote");
            System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête");
            System.out.println("7 -> Afficher la liste des pilotes arrivés");
            System.out.println("Entrez votre choix :");
            choix = scanner.nextInt();
            switch(choix){
                case 1: afficherLaCourse();
                    break;
                case 2: afficherLePiloteEnTete();
                    break;
                case 3: enregistrerDepassement();
                    break;
                case 4: disqualifierPilote();
                    break;
                case 5: donnerPosition();
                    break;
                case 6: faireFranchirArrivee();
                    break;
                case 7: afficherLesPilotesArrives();
            }
        } while (choix > 0 && choix <= 7);
        System.out.println("Merci de votre visite.");
    }


    private static void afficherLaCourse(){
        System.out.println(rallye.afficherPilotesEnLice());
    }

    private static void afficherLePiloteEnTete(){
        String piloteEnTete = rallye.donnerPiloteEnTete();
        if (piloteEnTete == null){
            System.out.println("Il n'y a plus aucun pilote en lice");
            return;
        }
        System.out.println(piloteEnTete);
    }

    private static void enregistrerDepassement(){
        System.out.println("Choisissez le pilote qui va dépasser celui devant lui :");
        String choix = scanner.nextLine();
        choix = scanner.nextLine();
        System.out.println(choix);
        boolean aReussi = rallye.faireDepasser(choix);
        if (!aReussi){
            System.out.println("Le pilote n'est pas dans la course ou il est déjà en tête.");
            return;
        }
        System.out.println("Le pilote a bien dépassé celui devant lui.");
    }

    private static void disqualifierPilote() {
        System.out.println("Choisissez le pilote qui va être disqualifié :");
        String choix = scanner.nextLine();
        choix = scanner.nextLine();
        System.out.println(choix);
        if (rallye.supprimer(choix)){
            System.out.println("La disqualification a réussi");
        } else{
            System.out.println("Erreur. Le pilote n'existe pas ou n'est plus en lice.");
        }
    }

    private static void donnerPosition(){
        System.out.println("Choisissez le pilote:");
        String choix = scanner.nextLine();
        choix = scanner.nextLine();
        System.out.println("Votre choix est le pilote " + choix);
        int position = rallye.position(choix);
        if (position == -1) System.out.println("Le pilote n'est pas en lice ou n'existe pas");
        else System.out.println("Le pilote est à la position numero " + position);
    }

    private static void faireFranchirArrivee(){
        String piloteEnTete = rallye.donnerPiloteEnTete();
        if (rallye.passerLigneArrivee(piloteEnTete)) System.out.println(piloteEnTete + " a passé la ligne d'arrivée avec succès");
        else System.out.println("Le pilote n'existe pas, n'est pas en tête ou n'est plus en lice");
    }

    private static void afficherLesPilotesArrives() {
        System.out.println(rallye.afficherPilotesArrives());
    }
}
