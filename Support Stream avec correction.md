```markdown
# Support de Cours : Streams en Java (4ème année IIR – EMSI)

---

## Introduction

Les **Streams en Java** introduits avec Java 8 facilitent le traitement expressif et performant des collections. Ils permettent des traitements séquentiels et parallèles tout en contribuant à un code concis, lisible et moderne.

**Objectifs**  
- Assimiler la motivation et les principes des Streams Java.
- Maîtriser les interfaces clés et les opérations intermédiaires/terminales.
- Manipuler les Streams sur des cas concrets en informatique et réseaux.
- Apprendre les bonnes pratiques pour des traitements puissants et sûrs.

---

## I. Notion de Stream 

Un **Stream** est une séquence d’éléments d’une source de données, traités via un pipeline d’opérations. Les Streams sont **immutables** (ne modifient pas la source) et l’exécution est **paresseuse** (réelle seulement lors d’une opération terminale).

**Motivations**  
- Faciliter la manipulation/ transformation des collections.
- Introduire un style de programmation fonctionnel moderne.
- Rendre les traitements parallèles accessibles facilement.

---

## II. Interfaces et Classes Essentielles

- `Stream<T>` : flux d’objets génériques.
- `IntStream`, `LongStream`, `DoubleStream` : flux spécialisés pour types primitifs.
- `Collectors`, `Stream.Builder`, etc.

| Interface              | Description                                |
|------------------------|---------------------------------------------|
| Stream<T>              | Flux d’éléments génériques                  |
| IntStream, LongStream  | Flux pour types primitifs (optimisés)       |
| BaseStream             | Super-interface (fermeture, parallelisme)   |
| Collectors             | Outils de collecte/agrégation               |

---

## III. Définition : Opérations Intermédiaires et Terminales

Les opérations sont soit **intermédiaires** (construction du pipeline), soit **terminales** (obtention du résultat final).  
- Les **opérations intermédiaires** transforment, filtrent, manipulent sans produire le résultat final ; elles renvoient un nouveau Stream et sont paresseuses.  
  - Ex : `filter`, `map`, `distinct`, `sorted`, `flatMap`

- Les **opérations terminales** déclenchent l’exécution, consomment le pipeline et produisent un résultat final (pas un Stream).  
  - Ex : `forEach`, `collect`, `reduce`, `count`, `anyMatch`, `allMatch`, `findFirst`

---

## IV. Exemples d’Utilisation

**Filtrer et transformer une liste**  
```

List<String> noms = Arrays.asList("ali", "asma", "reda", "amina");
List<String> resultat = noms.stream()
.filter(n -> n.length() > 3)
.map(String::toUpperCase)
.collect(Collectors.toList());
// [ASMA, REDA, AMINA]

```
**Somme des entiers pairs**  
```

int sommePairs = IntStream.range(1, 11).filter(n -> n % 2 == 0).sum();
// 2+4+6+8+10 = 30

```
**Stream parallèle (comptage de logs "ERROR")**  
```

long count = listDeLogs.parallelStream()
.filter(log -> log.contains("ERROR"))
.count();

```

---

## V. Cas d’Usages Informatique et Réseaux

**Traitement de logs système**  
```

List<String> erreurs = logs.stream()
.filter(l -> l.startsWith("[ERR]"))
.collect(Collectors.toList());

```
**Extraction d’IP**  
```

List<String> ips = lignes.stream()
.map(l -> extraireIP(l))
.filter(Objects::nonNull)
.distinct()
.collect(Collectors.toList());

```
**Statistiques sur flux**  
```

DoubleSummaryStatistics stats = values.stream()
.collect(Collectors.summarizingDouble(Double::doubleValue));
// stats.getAverage(), stats.getMax(), stats.getMin()

```
---

## VI. Parallélisme avec les Streams

Le parallélisme via `.parallelStream()` permet de tirer parti des cœurs CPU via le ForkJoinPool, utile sur de gros volumes de données.  
Attention à :
- l’ordre non garanti,
- l’absence de side effects (modification d’état partagé),
- l’intérêt uniquement sur des tâches lourdes ou volumineuses.

**Exemple**  
```

long t1 = System.currentTimeMillis();
long count = clients.parallelStream()
.filter(c -> c.getBalance() > 10000)
.count();
long t2 = System.currentTimeMillis();
System.out.println("Temps d'exécution : " + (t2 - t1) + " ms");

```

---

## VII. Traitements Avancés

**Streams infinis**  
```

Stream<Integer> entiers = Stream.iterate(0, n -> n + 1).limit(10);

```
**Agrégations/groupage**  
```

Map<Genre, List<Employe>> parGenre = employes.stream()
.collect(Collectors.groupingBy(Employe::getGenre));

```
**Combinaison d’opérations**  
```

List<ExpensiveResult> pipeline = data.stream()
.filter(this::isValid)
.filter(this::isRelevant)
.map(this::expensiveTransform)
.collect(Collectors.toList());

```
**FlatMap (aplatir des listes)**  
```

List<List<String>> groupes = ...;
groupes.stream().flatMap(List::stream).collect(Collectors.toList());

```

---

## VIII. Bonnes Pratiques et Limites

- Privilégier les streams pour les pipelines et gros volumes.
- Éviter les eff ets de bord dans les lambdas.
- Tester si le mode parallèle apporte un vrai gain.
- Un stream ne peut être consommé qu’une seule fois.

---

## IX. Exercices Streams Java

### Exercice 1  
Obtenir la liste des noms en majuscules contenant plus de 5 lettres (liste `noms`).

### Exercice 2  
Récupérer les nombres impairs, triés décroissant, dans une nouvelle liste.

### Exercice 3  
À partir d'une collection d'objets `Utilisateur` (`prenom`, `nom`, `age`), obtenir la liste triée des prénoms uniques des utilisateurs de moins de 25 ans.

### Exercice 4  
Extraire les logs contenant « CRITICAL », supprimer doublons et afficher le nombre total.

### Exercice 5  
Calculer la moyenne du prix des produits de catégorie « Informatique » (classe `Produit`).

### Exercice 6  
Filtrer les commandes (`Commande`) de montant > 1000 €, extraire/trier les dates croissantes dans une liste.

### Exercice 7  
Avec un flux de notes, obtenir la max, min et moyenne.

### Exercice 8  
À partir d'une liste `Employe` (nom, service), créer une Map service → liste des noms.

### Exercice 9  
Parmi une liste de chaînes, conserver celles contenant `'a'`, trier par longueur croissante, supprimer doublons.

### Exercice 10  
À partir d'une liste de `Livre` (`titre`, `auteur`, `année`), extraire titres publiés avant 2000 triés alpha.

---

## X. Corrections des Exercices

### Exercice 1
```

List<String> result = noms.stream()
.filter(n -> n.length() > 5)
.map(String::toUpperCase)
.collect(Collectors.toList());

```
### Exercice 2
```

List<Integer> result = nombres.stream()
.filter(n -> n % 2 != 0)
.sorted(Comparator.reverseOrder())
.collect(Collectors.toList());

```
### Exercice 3
```

List<String> result = utilisateurs.stream()
.filter(u -> u.getAge() < 25)
.map(Utilisateur::getPrenom)
.distinct()
.sorted()
.collect(Collectors.toList());

```
### Exercice 4
```

long count = logs.stream()
.filter(l -> l.contains("CRITICAL"))
.distinct()
.count();

```
### Exercice 5
```

OptionalDouble moyenne = produits.stream()
.filter(p -> p.getCategorie().equals("Informatique"))
.mapToDouble(Produit::getPrix)
.average();

```
### Exercice 6
```

List<LocalDate> dates = commandes.stream()
.filter(c -> c.getMontant() > 1000)
.map(Commande::getDate)
.sorted()
.collect(Collectors.toList());

```
### Exercice 7
```

IntSummaryStatistics stats = notes.stream()
.mapToInt(Integer::intValue)
.summaryStatistics();
// stats.getMax(), stats.getMin(), stats.getAverage()

```
### Exercice 8
```

Map<String, List<String>> map = employes.stream()
.collect(Collectors.groupingBy(
Employe::getService,
Collectors.mapping(Employe::getNom, Collectors.toList())
));

```
### Exercice 9
```

List<String> result = chaines.stream()
.filter(s -> s.contains("a"))
.distinct()
.sorted(Comparator.comparingInt(String::length))
.collect(Collectors.toList());

```
### Exercice 10
```

List<String> titres = livres.stream()
.filter(l -> l.getAnnee() < 2000)
.map(Livre::getTitre)
.sorted()
.collect(Collectors.toList());

```

---

## XI. Déclarations & Tests (Exercices 3 et 5 : version marocaine)

### Classe Utilisateur
```

public class Utilisateur {
private String prenom;
private String nom;
private int age;

    public Utilisateur(String prenom, String nom, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }
    public String getPrenom() { return prenom; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    }

```
### Test UtilisateurStream
```

import java.util.*;
import java.util.stream.*;

public class TestUtilisateurStream {
public static void main(String[] args) {
List<Utilisateur> utilisateurs = Arrays.asList(
new Utilisateur("Yassine", "El Amrani", 22),
new Utilisateur("Fatima", "Benhadda", 28),
new Utilisateur("Yassine", "Ait Lahcen", 19),
new Utilisateur("Jamila", "Kabbaj", 24),
new Utilisateur("Omar", "Berrada", 22),
new Utilisateur("Rania", "Ouazzani", 23),
new Utilisateur("Ahmed", "Maarouf", 21)
);

        List<String> result = utilisateurs.stream()
            .filter(u -> u.getAge() < 25)
            .map(Utilisateur::getPrenom)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    
        System.out.println(result); // [Ahmed, Jamila, Omar, Rania, Yassine]
    }
    }

```
### Classe Produit
```

public class Produit {
private String nom;
private String categorie;
private double prix;

    public Produit(String nom, String categorie, double prix) {
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
    }
    public String getNom() { return nom; }
    public String getCategorie() { return categorie; }
    public double getPrix() { return prix; }
    }

```
### Test ProduitStream
```

import java.util.*;
import java.util.stream.*;

public class TestProduitStream {
public static void main(String[] args) {
List<Produit> produits = Arrays.asList(
new Produit("PC Dell Mohammedia", "Informatique", 10500.0),
new Produit("Souris Casablanca", "Informatique", 190.0),
new Produit("Table berbère", "Mobilier", 700.0),
new Produit("Imprimante Rabat", "Informatique", 900.0),
new Produit("Chaise Essaouira", "Mobilier", 350.0),
new Produit("Routeur Maroc Telecom", "Informatique", 450.0)
);

        OptionalDouble moyenne = produits.stream()
            .filter(p -> p.getCategorie().equals("Informatique"))
            .mapToDouble(Produit::getPrix)
            .average();
    
        if (moyenne.isPresent()) {
            System.out.printf("Prix moyen Informatique : %.2f\n", moyenne.getAsDouble());
        } else {
            System.out.println("Aucun produit en catégorie Informatique.");
        }
        // Exemple d'affichage : Prix moyen Informatique : 2610.00
    }
    }

```
---

## XII. Conclusion

Les Streams modernisent la manipulation des collections et des flux en Java : performance, expressivité, parallélisme et concision. La maîtrise de leur utilisation et de leurs cas limites est un véritable atout pour l’ingénieur informatique d’aujourd’hui.

**Pistes pour aller plus loin** :
- Streams réactifs (Java 9+)
- Gestion avancée et tuning ForkJoinPool
- Intégration avec API réseau ou bases de données en mode flux
```

