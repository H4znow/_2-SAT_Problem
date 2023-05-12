PROJET : PROBLÈME 2-SAT<br>
UE : Algorithmique avancée et théorie des graphes<br>
Étudiant : HADDOU Amine (Licence Mathématique-Informatique)<br>

## Présentation du projet
Le problème 2-SAT est un problème de décision : étant donnée une formule 2-SAT P, il s’agit de répondre à la question « est-ce que la formule P est satisfaisable ou non ? ». Dans le cas particulier où les clauses sont de longueur 2, il existe des approches polynomiales. La plus connue est la suivante :
- Construire le graphe des implications.
- Calculer les composantes fortement connexes du graphe des implications.
- Si une composante contient à la fois un littéral et son opposé, la formule est insatisfaisable ; dans le cas contraire, la formule est satisfaisable.

Nous allons implémenter cette approche.

## Informations nécessaires pour faire fonctionner le code
Le code a été développé en Java 17.0.5 et a été géré avec Gradle 7.6. Il a été testé sur Windows 11 et il n'est pas garanti qu'il fonctionne correctement sur d'autres systèmes d'exploitation. Pour exécuter le code, vous devez installer le JDK et Gradle 7.6.

## Exécuter le code
Pour exécuter le code, suivez les étapes suivantes :
1. Ouvrez une invite de commande (ou un terminal) et naviguez jusqu'au répertoire racine du projet (répertoire contenant le fichier build.gradle ou build).
2. Exécutez la commande `gradlew build` pour compiler le projet.
3. Exécutez la commande `gradlew run` pour exécuter le projet.

## Fonctionnement général du code
J'ai essayé de commenter le code autant que possible sans le surcharger. Si vous souhaitez accéder à la JavaDoc, exécutez la commande `gradlew javadoc`. La JavaDoc sera ensuite disponible dans le répertoire `./build/docs/javadoc`. Je vous conseille d'ouvrir le fichier `package-summary.html` dans un premier temps.

Concernant le programme, voici son fonctionnement dans les grandes lignes :
1. Il accède au répertoire `./exemples` qui contient plusieurs fichiers contenant une formule au format DIMACS.
2. Il boucle sur chaque fichier afin de :
    1. Récupérer les clauses de la formule (la formule est considérée comme une conjonction dans le code).
    2. Créer le graphe des implications.
    3. Créer la transpose Gt du graphe.
    4. Déterminer l'ordre des sorties en parcourant en profondeur G.
    5. Déterminer les composantes fortement connexes en parcourant en profondeur Gt.
    6. Vérifier qu'aucune clause ne contient un littéral ainsi que sa négation.
    7. Afficher SAT ou NON-SAT en fonction des résultats obtenus.

Bien évidemment, pour vous assurer qu'il n'y a pas de triche, vous pouvez modifier les fichiers dans le répertoire `exemples` pour changer les formules, etc. Vérifiez simplement qu'il y ait au moins un fichier dans le répertoire pour ne pas générer d'erreur imprévue.

## Note de l'étudiant
La première version du programme ne fonctionnait que sur une formule. J'avais alors ajouté des classes pour afficher les graphes graphiquement en générant des fichiers PNG. Cependant, en relisant l'énoncé du projet, j'ai compris que le code devait tourner sur toutes les formules automatiquement. J'ai donc enlevé ces fonctionnalités par peur de faire planter/surcharger la mémoire de la machine. J'espère que ces efforts seront pris en compte.