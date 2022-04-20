import java.util.LinkedList;
import java.util.Scanner;

public class GestionnaireEntrepot {

    private static Scanner scanner = new Scanner(System.in);
    private static Entrepot entrepot;

    public static void main(String[] args) {

        System.out.println("********************************");
        System.out.println("GESTION ENTREPOT");
        System.out.println("********************************");
        System.out.println("Entrez le nombre de hangars disponibles dans l'entrepot :");

        int choix = scanner.nextInt();
        entrepot = new Entrepot(choix);

        do {
            System.out.println("1 -> Attribuer un hangar");
            System.out.println("2 -> Libérer un hangar");
            System.out.println("3 -> Lister les hangars d'une société");
            System.out.println("4 -> Lister les numéros des hangars libres");
            System.out.println("Entrez votre choix :");
            choix = scanner.nextInt();
            switch(choix){
                case 1: attribuerHangar();
                    break;
                case 2: libererUnHangar();
                    break;
                case 3: listerHangarsDeLaSociete();
                    break;
                case 4: listerHangarsLibres();
                    break;
            }
        } while (choix > 0 && choix <= 4);
        System.out.println("Merci de votre visite.");
    }

    public static void attribuerHangar() {
        if (entrepot.estPlein()){
            System.out.println("Désolé, l'entrepôt est plein !");
            return;
        }

        System.out.println("Entrez le numéro de la société :");
        int numSoc = scanner.nextInt();
        String nomSociete = entrepot.getNomSociete(numSoc);
        if (nomSociete == null){
            System.out.println("Cette société n'est pas encore enregistrée dans l'entrepôt.");
            System.out.println("Entrez un nom pour cette société :");
            scanner.nextLine();
            nomSociete = scanner.nextLine();
        } else System.out.println("Cette société est répertoriée dans l'entrepôt.");
        int hangarAttribue = entrepot.attribuerHangar(numSoc,nomSociete);
        System.out.println("La société " + nomSociete + " s'est fait attribuer le hangar numéro " + hangarAttribue);
    }

    public static void listerHangarsDeLaSociete() {
        System.out.println("Entrez le numéro de la société :");
        int numSoc = scanner.nextInt();
        Societe societe = entrepot.getSociete(numSoc);
        if (societe == null){
            System.out.println("Désolé, cette société n'est pas présente dans l'entrepôt.");
            return;
        }
        System.out.println("Voici la liste des hangars possédés par la société " + societe.getNom());
        System.out.println(societe.lesHangars());
    }

    public static void libererUnHangar() {
        if (entrepot.estVide()){
            System.out.println("L'entrepôt est déjà vide !");
            return;
        }
        System.out.println("Entrez le numéro du hangar à libérer :");
        int numHangar = scanner.nextInt();
        if (!entrepot.libererHangar(numHangar)) System.out.println("Le hangar numéro " + numHangar + " est déjà libre.");
        else {
            System.out.println("Le hangar numéro " + numHangar + " s'est libéré.");
        }
    }

    public static void listerHangarsLibres() {
        if (entrepot.estPlein()){
            System.out.println("Aucun entrepôt n'est libre.");
            return;
        };
        System.out.println(entrepot.getNumeroHangarsLibres());
    }
}
