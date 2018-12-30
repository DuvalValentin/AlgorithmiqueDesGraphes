# AlgorithmiqueDesGraphes
Version 1.0.4
Le but de ces packages est de disposer d'un ensemble d'outils facile à utiliser permettant de manipuler des Graphes.
L'objectif final étant de pouvoir effectuer un maximum  d'algorithmes sur les graphes.

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

V 1.0.3

Des tests unitaires ont été réalisés avec JUnit pour chacune des classes et ce pour la plupart des méthodes.
Les méthodes ajoutSommet et supprSommet ont été ajoutées.
X et Gamma dans le Graphe ont été mieux encapsulés pour empêcher des modifications sur ses deux ensembles sans passer par le Graphe, les utilisateurs n'ont (normalement) plus accès aux graphes.
La plupart des this on été supprimés car ils n'étaient pas utiles.

V 1.0.4

Ajouter les coût a été moins facile que prévu, il y a entre autre un problème de conception (voir schéma DIA), des classes ont été créées mais elles ne sont pas encore utilisables.
Les setters de Graphe ont été rendus privés pour une meilleure encapsulation.
Les méthodes add de EnsembleArc et ajouteArc,supprArc de Graphe peuvent maintenant prendre en arguments deux sommets(représentant un arc).
Les algorithmes de fermeture transitives (Puissace de graphes,RoyWarshall) ont été rajoutés.
La classe AlgorithmeGraphe a été scindée en plusieurs autre classes (Parcours et FermetureTransitive).
Des tests unitaires ont été ajoutés au fur et à mesure de l'ajout de classes/méthodes

V 1.0.5.0

Les coûts ont enfin été ajoutés:
- Une class Cout comprenant un flottant a été créée.
- La classe ArcValue hérite de Arc et comporte un attribut Cout.
- EnsembleArc et EnsembleArcValue descendent de AbstractEnsembleArc, en conséquence EnsembleArcValue ne peut contenir que des ArcValue et pas d'Arc.
- Graphe et GrapheValue descendent de AbstractGraphe.

Les algorithmes de graphes ont été décomposés en plusieurs sous parties:
-FermetureTransitive
-Parcours
-DetectionCircuit
-AntiTransitif

Remarque : 
-La méthode firstSommet de EnsembleSommet est un peu crade donc est à modifier dès que possible.
-Essayer de voir si on peut trouver une manière de réexploiter facilement des Algorithme/Méthodes (exemple : ne pas avoir à écrire tout RoyWarshall lors de la fermeture anti-Transitive) afin de rendre le projet plus facilement 

Prochaine étape : revenir sur le code, ajouter des Interfaces, mettre à jour les tests, possiblement résoudre les problèmes mis en remarque.