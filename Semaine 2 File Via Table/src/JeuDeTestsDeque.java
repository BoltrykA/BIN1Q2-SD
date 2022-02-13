/**
 * @author Alicia Boltryk
 */

public class JeuDeTestsDeque {
    /**
     * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
     *
     * @param messageErreur message a afficher en cas de probleme
     * @param attendu la valeur qu'on s'attendait a recevoir
     * @param recu la valeur qu'on a recu en realite
     */
    private static void assertEquals(String messageErreur, Object attendu, Object recu) {
        if (attendu==null) {
            if (recu!=null) {
                System.out.println(messageErreur+". Attendu="+attendu+" reçu="+recu);
                System.exit(0);
            }
        } else if (!attendu.equals(recu)) {
            System.out.println(messageErreur+". Attendu="+attendu+" reçu="+recu);

        }
    }

    public static void main(String[] args) {

        DequeImpl<Character> deque = new DequeImpl<>();

        //Test 1
        System.out.println("Test 1 : premier ajout (en dernier)");
        deque.ajouterEnDernier('a');
        assertEquals("test 1 ko", 1, deque.taille());
        assertEquals("test 1 ko", " a", deque.toString());

        //Test 2
        System.out.println("Test 2 : ajout en premier (z)");
        deque.ajouterEnPremier('z');
        assertEquals("test 2 ko", 2, deque.taille());
        assertEquals("test 2 ko", " z a", deque.toString());


        //Test 3
        System.out.println("Test 3 : ajout en dernier (b)");
        deque.ajouterEnDernier('b');
        assertEquals("test 3 ko", 3, deque.taille());
        assertEquals("test 3 ko", " z a b", deque.toString());

        //Test 4
        System.out.println("Test 4 : premier (z)");
        char p = deque.premier();
        assertEquals("test 4 ko", 'z', p);
        assertEquals("test 4 ko", 3, deque.taille());
        assertEquals("test 4 ko", " z a b", deque.toString());

        //Test 5
        System.out.println("Test 5 : retirer en premier (z)");
        p = deque.retirerEnPremier();
        assertEquals("test 5 ko", 'z', p);
        assertEquals("test 5 ko", 2, deque.taille());
        assertEquals("test 5 ko", " a b", deque.toString());


        //Test 6
        System.out.println("Test 6 : retirer en dernier (b)");
        p = deque.retirerEnDernier();
        assertEquals("test 6 ko", 'b', p);
        assertEquals("test 6 ko", 1, deque.taille());
        assertEquals("test 6 ko", " a", deque.toString());

        //test 7
        System.out.println("Test 7 : retirer en dernier (a)");
        p = deque.retirerEnDernier();
        assertEquals("test 7 ko", 'a', p);
        assertEquals("test 7 ko", 0, deque.taille());
        assertEquals("test 7 ko", "", deque.toString());

        //test 8
        System.out.println("Test 8 : retirer en premier (exception)");
        try{
           deque.retirerEnPremier();
            System.out.println("test 8 ko");
            return;
        } catch (FileVideException e){}

        //Test 9
        System.out.println("Test 9 : retirer en dernier (exception)");
        try{
            deque.retirerEnDernier();
            System.out.println("test 9 ko");
            return;
        } catch (FileVideException e){}

        //test 10
        System.out.println("Test 10 : premier (exception)");
        try{
            deque.premier();
            System.out.println("test 10 ko");
            System.exit(0);
        } catch (FileVideException e){}

        //test 11
        System.out.println("Test 11 : ajouter en premier a, puis b, puis c");
        deque.ajouterEnPremier('a');
        deque.ajouterEnPremier('b');
        deque.ajouterEnPremier('c');
        assertEquals("test 11 ko", 3, deque.taille());
        assertEquals("test 11 ko", " c b a", deque.toString());

        //Test 12
        System.out.println("Test 12 : dernier");
        p = deque.dernier();
        assertEquals("test 12 ko", 'a', p);
        assertEquals("test 12 ko", 3, deque.taille());
        assertEquals("test 12 ko", " c b a", deque.toString());

        //Test 13
        System.out.println("Test 13 : ajouter en premier z");
        deque.ajouterEnPremier('z');
        assertEquals("test 13 ko", 4, deque.taille());
        assertEquals("test 13 ko", " z c b a", deque.toString());

        //Test 14
        System.out.println("Test 14 : premier");
        p = deque.premier();
        assertEquals("test 14 ko", 'z', p);
        assertEquals("test 14 ko", 4, deque.taille());
        assertEquals("test 14 ko", " z c b a", deque.toString());

        //Test 15
        System.out.println("Test 15 : retirer en dernier (a)");
        p = deque.retirerEnDernier();
        assertEquals("test 15 ko", 'a', p);
        assertEquals("test 15 ko", 3, deque.taille());
        assertEquals("test 15 ko", " z c b", deque.toString());

        System.out.println("Tous les tests ont réussi !");


    }
}
