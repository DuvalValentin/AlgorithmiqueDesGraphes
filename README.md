# AlgorithmiqueDesGraphes
Version 1
Le but de ce package est de disposer d'un ensemble d'outils facile à utiliser permettant de manipuler des Graphes.
L'objectif final étant de pouvoir effectuer des algorithmes sur les graphes.


Actuellement ce package ne dispose que d'objets basiques : 
-Sommet : prend un objet quelconque en paramètre
-Arc : comporte 2 sommets.
-EnsembleSommet : HashSet de Sommets.
-EnsembleArc : HashSet d'Arc.
-Graphe : comporte un EnsembleSommet et un EnsembleArc formant ainsi un graphe.

Pour le moment il n'y a que des setters et des getters définis ainsi que les méthodes toString qui ont été redéfinies.

Prochaines étapes : 
-Modifier les méthodes equals pour mieux comparer les différents objets.
-Prévoir des contrôle dans l'EnsembleArc du Graphe pour vérifier qu'il ne contienne que des Arcs contenant des Sommets appartenant à EnsembleSommet.
