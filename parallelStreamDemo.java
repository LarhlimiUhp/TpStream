import java.util.*;
import java.util.stream.*;

public class ParallelStreamExample {

    public static void main(String[] args) {
        exemplesDeBase();
        System.out.println("\n" + "=".repeat(60) + "\n");
        comparaisonPerformance();
        System.out.println("\n" + "=".repeat(60) + "\n");
        exempleDangers();
        System.out.println("\n" + "=".repeat(60) + "\n");
        exempleBonneUtilisation();
    }

    /**
     * EXEMPLE 1 : Bases du Parallel Stream
     */
    public static void exemplesDeBase() {
        System.out.println("=== EXEMPLES DE BASE ===\n");

        List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Stream séquentiel classique
        System.out.println("Stream séquentiel:");
        nombres.stream()
                .map(n -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " - Traite: " + n);
                    return n * 2;
                })
                .forEach(n -> {});

        System.out.println("\nStream parallèle:");
        // Parallel Stream - Le travail est divisé entre plusieurs threads
        nombres.parallelStream()
                .map(n -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " - Traite: " + n);
                    return n * 2;
                })
                .forEach(n -> {});

        // Notez que l'ordre de traitement n'est PAS garanti en parallèle!
    }

    /**
     * EXEMPLE 2 : Comparaison de performance
     * Le parallel stream est efficace pour les traitements coûteux sur de grandes collections
     */
    public static void comparaisonPerformance() {
        System.out.println("=== COMPARAISON DE PERFORMANCE ===\n");

        // Créer une grande liste de nombres
        List<Integer> grandeCollection = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());

        // Test avec Stream séquentiel
        long debut1 = System.currentTimeMillis();
        long somme1 = grandeCollection.stream()
                .filter(n -> n % 2 == 0)  // Garder les pairs
                .mapToLong(n -> n * n)     // Calculer le carré
                .sum();
        long fin1 = System.currentTimeMillis();
        System.out.println("Stream séquentiel:");
        System.out.println("  Résultat: " + somme1);
        System.out.println("  Temps: " + (fin1 - debut1) + " ms");

        // Test avec Parallel Stream
        long debut2 = System.currentTimeMillis();
        long somme2 = grandeCollection.parallelStream()
                .filter(n -> n % 2 == 0)  // Garder les pairs
                .mapToLong(n -> n * n)     // Calculer le carré
                .sum();
        long fin2 = System.currentTimeMillis();
        System.out.println("\nStream parallèle:");
        System.out.println("  Résultat: " + somme2);
        System.out.println("  Temps: " + (fin2 - debut2) + " ms");
        System.out.println("  Gain: " + String.format("%.2f", (double)(fin1-debut1)/(fin2-debut2)) + "x plus rapide");

        /*
         * RÈGLE : Le parallel stream est bénéfique quand:
         * 1. La collection est GRANDE (milliers d'éléments)
         * 2. Les opérations sont COÛTEUSES (calculs complexes, I/O)
         * 3. Pas de dépendances entre les éléments
         */
    }

    /**
     * EXEMPLE 3 : LES DANGERS du Parallel Stream
     * Démonstration des problèmes de concurrence
     */
    public static void exempleDangers() {
        System.out.println("=== DANGERS DU PARALLEL STREAM ===\n");

        List<Integer> nombres = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        // DANGER 1: Modification d'une variable extérieure (NON thread-safe)
        List<Integer> resultats = new ArrayList<>();  // ArrayList n'est PAS thread-safe!

        System.out.println("DANGER: Modification de liste externe");
        nombres.parallelStream()
                .map(n -> n * 2)
                .forEach(resultats::add);  // PROBLÈME: Race condition!

        System.out.println("  Taille attendue: 1000");
        System.out.println("  Taille obtenue: " + resultats.size()); // Peut être < 1000 !
        System.out.println("  ⚠️  Des éléments ont été perdus à cause de la concurrence!\n");

        // DANGER 2: Ordre non garanti
        System.out.println("DANGER: Ordre non respecté");
        System.out.print("  Premiers éléments en parallèle: ");
        nombres.parallelStream()
                .limit(10)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n  ⚠️  L'ordre n'est pas 1, 2, 3, 4...\n");

        /*
         * RÈGLES DE SÉCURITÉ:
         * - Ne JAMAIS modifier des variables extérieures
         * - Utiliser .collect() au lieu de .forEach() pour accumuler
         * - Si l'ordre compte, utiliser .forEachOrdered() ou stream séquentiel
         */
    }

    /**
     * EXEMPLE 4 : BONNE utilisation du Parallel Stream
     * Cas d'usage réels et bonnes pratiques
     */
    public static void exempleBonneUtilisation() {
        System.out.println("=== BONNES PRATIQUES ===\n");

        // CAS 1: Traitement d'une liste de clients
        List<Client> clients = genererClients(10000);

        System.out.println("Cas 1: Filtrage et transformation de clients");
        long debut = System.currentTimeMillis();

        // Utilisation CORRECTE: collect() est thread-safe
        List<String> clientsVIP = clients.parallelStream()
                .filter(c -> c.getSolde() > 50000)           // Filtre: clients riches
                .filter(c -> c.getAnneeInscription() < 2020) // Filtre: anciens clients
                .map(c -> c.getNom().toUpperCase())          // Transformation
                .sorted()                                     // Tri (attention: coûteux!)
                .collect(Collectors.toList());               // Collection thread-safe

        long fin = System.currentTimeMillis();
        System.out.println("  Clients VIP trouvés: " + clientsVIP.size());
        System.out.println("  Temps: " + (fin - debut) + " ms");
        System.out.println("  Premiers: " + clientsVIP.stream().limit(3).collect(Collectors.toList()));

        // CAS 2: Calculs d'agrégation
        System.out.println("\nCas 2: Calculs statistiques");

        // Calcul du solde moyen des clients actifs
        double soldeMoyen = clients.parallelStream()
                .filter(Client::isActif)
                .mapToDouble(Client::getSolde)
                .average()
                .orElse(0.0);

        System.out.println("  Solde moyen: " + String.format("%.2f", soldeMoyen) + " MAD");}}