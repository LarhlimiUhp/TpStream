<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# explique document compplet

Voici une explication complète du document affiché sur l’image, qui propose un exercice de gestion d’un magasin de vêtements en Java, en mobilisant l’API Streams.

***

# Exercice 3 : Gestion d'un magasin de vêtements avec Streams

Ce TP s’adresse à des étudiants de 4e année IIR (EMSI) et vise à mettre en pratique la manipulation des flux de données (Streams) en Java à travers une application réaliste de gestion de stock en magasin de vêtements.

***

## 1. Créer une classe Vêtement (`ClothingItem`)

Chaque vêtement du magasin sera modélisé par un objet contenant :

- **id** (`int`) : identifiant unique de l’article
- **name** (`String`) : nom du vêtement (ex : “T-shirt Rouge”)
- **category** (`String`) : catégorie (ex : “T-shirt”, “Pantalon”)
- **price** (`double`) : prix de vente
- **stock** (`int`) : quantité disponible en stock
- **onSale** (`boolean`) : indique si l’article est en promotion (true/false)

**À faire :**

- Écrire la classe avec les 6 attributs ci-dessus
- Ajouter un constructeur avec paramètres pour initialiser tous les champs
- Ajoutez une méthode `toString` qui retourne tous les champs sous forme lisible

***

## 2. Créer une classe Magasin (`Store`)

Cette classe va gérer tous les vêtements du magasin.

### Attributs :

- Une liste d’objets `ClothingItem` (dans l’exercice, il est suggéré d’utiliser une `LinkedList`)


### Constructeur :

- Un constructeur **sans paramètre** qui initialise la liste des vêtements


### Méthodes demandées : (chacune utilisant des Streams de Java)

#### a. Lister les vêtements en promotion

- Filtrer uniquement les articles où `onSale` est à `true`
- Afficher leur nom et leur prix


#### b. Trouver les 3 articles les moins chers

- Trier la liste par prix croissant
- Afficher les 3 premiers


#### c. Calculer la valeur totale du stock

- Pour chaque article : `prix * quantité en stock`
- Additionner pour obtenir la valeur totale du stock du magasin


#### d. Regrouper les vêtements par catégorie

- Utiliser `Collectors.groupingBy` pour organiser la liste dans une `Map<String, List<ClothingItem>>`
- Cette structure permet de retrouver tous les articles par catégorie


#### e. Lister les noms des vêtements avec un stock > 20

- Filtrer ceux dont stock > 20
- Collecter leurs noms dans une liste


#### f. Vérifier si tous les articles sont en stock

- Utiliser `allMatch` pour vérifier si chaque article a `stock > 0`


#### g. Appliquer une remise sur les articles en promotion

- Créer un nouveau Stream filtrant sur `onSale == true`
- Réduire le prix de 20% pour ces articles
- Collecter le résultat dans une nouvelle liste


#### h. Créer un affichage personnalisé pour chaque article

- Utiliser `map` pour formater chaque article selon le modèle :
    - `"ID: 1 | Nom: T-shirt Rouge | Prix: 159dh | Stock: 50"`

***

## Synthèse pédagogique

Ce TP vous demande de :

- Modéliser les objets (`ClothingItem`, `Store`)
- Manipuler les collections (ajouts dans la liste, parcours, filtrage…)
- Utiliser l’API `Stream` de Java pour :
    - **Filtrer** (`filter`), **transformer** (`map`), **trier** (`sorted`), **limiter** (`limit`), **agréger** (`sum`, `allMatch`) et **regrouper** (`collect`)
- Comprendre les traitements fonctionnels, très utilisés aujourd’hui en programmation Java avancée

***

### Objectifs d’apprentissage

- Maîtriser la manipulation des listes et flux avec l’API Stream en Java
- Savoir effectuer des traitements classiques (filtrage, mapping, groupe, statistiques, réduction…)
- Comprendre l’intérêt de la programmation fonctionnelle et des *collectors* pour traiter de grands jeux de données en entreprise

***

Ce document est une excellente base pour un mini-projet ou un TP long, permettant de combiner POO, collections et Streams de manière concrète et très proche des besoins des applications modernes en Java.

