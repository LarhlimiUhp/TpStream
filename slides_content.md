# Introduction aux Streams Java

## Slide 1: Titre
**Titre :** Les Streams en Java : Programmation Fonctionnelle pour la Manipulation de Données
**Sous-titre :** Utilisation de la classe `Client` pour des exemples concrets
**Auteur :** Manus AI

## Slide 2: Qu'est-ce qu'un Stream ?
**Titre :** Le Stream : Une Abstraction Déclarative
**Points Clés :**
*   **Définition :** Séquence d'éléments supportant des opérations agrégées. Introduit avec Java 8.
*   **Objectif :** Traiter les données de manière déclarative (Quoi faire) plutôt qu'impérative (Comment le faire).
*   **Avantages :** Code plus concis, lisible et facilite le parallélisme.
*   **Pipeline :** Un Stream se construit en 3 étapes : **Source** → **Opérations Intermédiaires** → **Opération Terminale**.

## Slide 3: La Classe `Client` (Source de Données)
**Titre :** Notre Modèle de Données : La Classe `Client`
**Points Clés :**
*   **Structure :** `Client(id_client, nom, adresse, chiffreAffaire)`.
*   **Rôle :** Servira de source de données (`List<Client>`) pour tous nos exemples et exercices.
*   **Code :** La source est une `List<Client>` générée par `ClientData.getClients()`.
*   **Exemple de données :** Clients de Paris, Lyon, Marseille, avec des CA variés.

## Slide 4: Opérations Intermédiaires (1/2)
**Titre :** Transformation et Filtrage des Données
**Points Clés :**
*   **`filter(Predicate)` :** Sélectionne les éléments qui satisfont une condition.
    *   *Exemple :* `filter(c -> c.getChiffreAffaire() > 500000)`
*   **`map(Function)` :** Transforme chaque élément en un nouvel élément.
    *   *Exemple :* `map(Client::getNom)` pour obtenir les noms.
*   **Nature :** Ces opérations sont **paresseuses (lazy)** et ne s'exécutent qu'à l'appel de l'opération terminale.

## Slide 5: Opérations Intermédiaires (2/2)
**Titre :** Tri, Limitation et Unicité
**Points Clés :**
*   **`sorted(Comparator)` :** Trie les éléments du Stream.
    *   *Exemple :* `sorted(Comparator.comparing(Client::getNom))`
*   **`limit(n)` / `skip(n)` :** Permet de paginer ou de limiter le nombre de résultats.
    *   *Exemple :* `limit(3)` pour les 3 premiers clients.
*   **`distinct()` :** Supprime les doublons (basé sur `equals()` et `hashCode()`).
    *   *Exemple :* `map(Client::getAdresse).distinct()` pour les villes uniques.

## Slide 6: Opérations Terminales : Collecte
**Titre :** `collect()` : Accumuler les Résultats
**Points Clés :**
*   **Rôle :** Consomme le Stream et accumule les éléments dans une structure de données finale.
*   **`Collectors.toList()` / `toSet()` :** Les plus simples pour créer des Collections.
*   **`Collectors.toMap()` :** Pour créer une Map (ex: ID -> Nom).
*   **`Collectors.groupingBy()` :** Regroupement puissant (ex: Clients par ville).

## Slide 7: Opérations Terminales : Réduction et Statistiques
**Titre :** Agrégation et Calculs
**Points Clés :**
*   **`reduce()` :** Combine les éléments du Stream en un seul résultat.
    *   *Exemple :* Calcul du CA total : `reduce(0.0, Double::sum)`
*   **Statistiques :** `count()`, `min()`, `max()`, `average()`.
    *   *Exemple :* Trouver le client avec le CA maximum : `max(Comparator.comparing(Client::getChiffreAffaire))`
*   **Streams Primitifs :** Utiliser `IntStream`, `DoubleStream` (`mapToDouble`) pour éviter l'autoboxing.

## Slide 8: Opérations Terminales : Recherche et Vérification
**Titre :** Vérification de Conditions
**Points Clés :**
*   **`anyMatch(Predicate)` :** Vrai si au moins un élément correspond.
*   **`allMatch(Predicate)` :** Vrai si tous les éléments correspondent.
*   **`findFirst()` / `findAny()` :** Retournent un `Optional<T>` contenant un élément trouvé.
    *   *Utile :* Pour vérifier l'existence d'un client avec un CA élevé.

## Slide 9: Exercice 1 : Filtrage et Tri
**Titre :** Exercice Pratique 1 : Filtrage et Tri
**Problème :** Trouver tous les clients résidant à **"Lyon"** et les trier par **chiffre d'affaires décroissant**.
**Solution (Pipeline) :**
1.  `stream()`
2.  `filter(c -> "Lyon".equals(c.getAdresse()))`
3.  `sorted(Comparator.comparing(Client::getChiffreAffaire).reversed())`
4.  `collect(Collectors.toList())`

## Slide 10: Exercice 4 : Regroupement Complexe
**Titre :** Exercice Pratique 2 : Regroupement et Agrégation
**Problème :** Regrouper les clients par **ville** et calculer le **chiffre d'affaires moyen** par ville.
**Solution (Pipeline) :**
1.  `stream()`
2.  `collect(Collectors.groupingBy(`
3.  `    Client::getAdresse,`
4.  `    Collectors.averagingDouble(Client::getChiffreAffaire)`
5.  `))`

## Slide 11: Bonnes Pratiques
**Titre :** Bonnes Pratiques et Performance
**Points Clés :**
*   **Immutabilité :** Les Streams ne modifient pas la source de données.
*   **Lazy Evaluation :** Les intermédiaires ne s'exécutent qu'une fois l'opération terminale appelée.
*   **Streams Parallèles :** Utiliser `parallelStream()` pour les gros volumes de données, mais attention aux coûts de synchronisation.
*   **Éviter l'Autoboxing :** Préférer `IntStream`, `DoubleStream` pour les calculs numériques.

## Slide 12: Conclusion
**Titre :** Conclusion et Ressources
**Points Clés :**
*   L'API Stream est essentielle pour une programmation Java moderne, fonctionnelle et performante.
*   Maîtriser le pipeline (Source -> Intermédiaire -> Terminal) est la clé.
*   **Ressources :** Documentation officielle Oracle, Tutoriels Baeldung.
*   **Questions ?**
