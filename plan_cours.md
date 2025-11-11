# Plan Détaillé du Support de Cours : Les Streams en Java

## I. Introduction aux Streams Java
1.  Qu'est-ce qu'un Stream ? (Définition, historique, motivation)
2.  Pourquoi utiliser les Streams ? (Avantages : concision, lisibilité, parallélisme)
3.  Différence entre Collections et Streams (Stockage vs. Traitement)
4.  Les trois étapes d'un Stream : Source, Opérations Intermédiaires, Opération Terminale

## II. Préparation : La Classe `Client`
1.  Définition de la classe `Client` (avec `id_client`, `nom`, `adresse`, `chiffre_affaire`).
2.  Implémentation des méthodes `toString()`, `equals()`, `hashCode()`.
3.  Création d'une liste de données d'exemple (`List<Client>`).

## III. Les Opérations Intermédiaires (Lazy Operations)
1.  **Filtrage** : `filter()`
    *   Exemple : Filtrer les clients ayant un chiffre d'affaires supérieur à un seuil.
2.  **Transformation** : `map()` et `flatMap()`
    *   Exemple : Transformer une liste de `Client` en une liste de leurs noms.
3.  **Tri** : `sorted()`
    *   Exemple : Trier les clients par nom ou par chiffre d'affaires.
4.  **Limitation/Saut** : `limit()` et `skip()`
    *   Exemple : Afficher les 5 premiers clients.
5.  **Déduplication** : `distinct()`
    *   Exemple : Supprimer les doublons (si la méthode `equals` est bien implémentée).

## IV. Les Opérations Terminales (Eager Operations)
1.  **Collecte** : `collect()`
    *   Utilisation de `Collectors.toList()`, `toSet()`, `toMap()`.
    *   Utilisation de `Collectors.groupingBy()` (Regroupement par adresse).
    *   Utilisation de `Collectors.partitioningBy()`.
2.  **Itération** : `forEach()`
3.  **Recherche** : `findFirst()`, `findAny()`, `anyMatch()`, `allMatch()`, `noneMatch()`
    *   Exemple : Vérifier si au moins un client a un CA > 1 000 000.
4.  **Réduction** : `reduce()`
    *   Exemple : Calculer le chiffre d'affaires total.
5.  **Statistiques** : `count()`, `min()`, `max()`, `average()` (avec `summarizingDouble`).

## V. Les Streams Primitifs
1.  `IntStream`, `LongStream`, `DoubleStream`.
2.  Conversion entre `Stream<T>` et Streams Primitifs (`mapToInt`, `boxed`).

## VI. Exercices Pratiques (Basés sur la classe `Client`)
1.  **Exercice 1 : Filtrage et Tri** (Trouver les clients d'une ville spécifique, triés par nom).
2.  **Exercice 2 : Transformation et Collecte** (Obtenir une `Map<String, Client>` où la clé est le nom du client).
3.  **Exercice 3 : Agrégation** (Calculer le chiffre d'affaires moyen des clients).
4.  **Exercice 4 : Opérations Complexes** (Regrouper les clients par ville et calculer le CA total par ville).
5.  **Exercice 5 : Vérification** (Vérifier si tous les clients ont un ID unique).

## VII. Solutions des Exercices
(Code Java complet pour chaque exercice)

## VIII. Conclusion
1.  Rappel des bonnes pratiques.
2.  Introduction aux Streams Parallèles (`parallelStream()`).
3.  Ressources supplémentaires.
