# AlgorithmiqueDesGraphes
Version 1.0.2
Le but de ce package est de disposer d'un ensemble d'outils facile à utiliser permettant de manipuler des Graphes.
L'objectif final étant de pouvoir effectuer des algorithmes sur les graphes.

V 1
Actuellement ce package ne dispose que d'objets basiques : 
-Sommet : contient un attribut Id qui peut-être n'importe quel objet.
-Arc : comporte 2 sommets.
-EnsembleSommet : HashSet de Sommets.
-EnsembleArc : HashSet d'Arc.
-Graphe : comporte un EnsembleSommet et un EnsembleArc formant ainsi un graphe.

Pour le moment il n'y a que des setters et des getters définis ainsi que les méthodes toString qui ont été redéfinies.

V 1.0.1

Les méthodes equals et HashCode ont été redéfinies pour les sommets et les arcs, ainsi deux sommet ayant le même objet en Id ne peuvent être ajoutés dans un même EnsembleSommet, même chose pour deux arcs ayant le mêmes Sommets de depart et d'arrivée.
De même ces méthodes ont été modifiées pour les Graphes afin que les Graphes aient des hashCode corrects et qu'ils puissent être comparés à d'autre Graphes.

V 1.0.2

Plus aucun warning signalés dans le projet (@SuppressWarnings("unchecked") pour les méthode incriminées)
Impémentation des méthodes dites élémentaires ( liste de successeur/prédeccésseur ; existence des sommets/arcs/boucles ; ajout/suppression d'arcs dans un graphe.
Avec ces nouvelles méthodes les algorithmes de parcours DFS et WFS ont pu être crées dans une nouvelle classe : AlgorithmeGraphe (classe qui permetra d'appeller des algoritmes de manière statique, les méthodes algorithme prennent des graphes en paramètre.)
La classe Test a été renommée en ExempleUtilisation

Prochaine étape : 
-Apprendre à maîtriser les assert en en mettre dans le projet.
-Revenir sur les différentes méthodes et effectuer les modification nécessaires pour rendre le code plus propre possible.

