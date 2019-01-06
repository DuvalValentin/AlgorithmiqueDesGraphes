# AlgorithmiqueDesGraphes
Version 0.8
Le but de ces packages est de disposer d'un ensemble d'outils facile à utiliser permettant de manipuler des Graphes.
L'objectif final étant de pouvoir effectuer un maximum  d'algorithmes sur les graphes.

V 0.1
Actuellement ce package ne dispose que d'objets basiques : 
- Sommet : contient un attribut Id qui peut-être n'importe quel objet.
- Arc : comporte 2 sommets.
- EnsembleSommet : HashSet de Sommets.
- EnsembleArc : HashSet d'Arc.
- Graphe : comporte un EnsembleSommet et un EnsembleArc formant ainsi un graphe.

Pour le moment il n'y a que des setters et des getters définis ainsi que les méthodes toString qui ont été redéfinies.

V 0.2

Les méthodes equals et HashCode ont été redéfinies pour les sommets et les arcs, ainsi deux sommet ayant le même objet en Id ne peuvent être ajoutés dans un même EnsembleSommet, même chose pour deux arcs ayant le mêmes Sommets de depart et d'arrivée.
De même ces méthodes ont été modifiées pour les Graphes afin que les Graphes aient des hashCode corrects et qu'ils puissent être comparés à d'autre Graphes.

V 0.3

Plus aucun warning signalés dans le projet (@SuppressWarnings("unchecked") pour les méthode incriminées)
Impémentation des méthodes dites élémentaires ( liste de successeur/prédeccésseur ; existence des sommets/arcs/boucles ; ajout/suppression d'arcs dans un graphe.
Avec ces nouvelles méthodes les algorithmes de parcours DFS et WFS ont pu être crées dans une nouvelle classe : AlgorithmeGraphe (classe qui permetra d'appeller des algoritmes de manière statique, les méthodes algorithme prennent des graphes en paramètre.)
La classe Test a été renommée en ExempleUtilisation

V 0.4

Des tests unitaires ont été réalisés avec JUnit pour chacune des classes et ce pour la plupart des méthodes.
Les méthodes ajoutSommet et supprSommet ont été ajoutées.
X et Gamma dans le Graphe ont été mieux encapsulés pour empêcher des modifications sur ses deux ensembles sans passer par le Graphe, les utilisateurs n'ont (normalement) plus accès aux graphes.
La plupart des this on été supprimés car ils n'étaient pas utiles.

V 0.5

Ajouter les coût a été moins facile que prévu, il y a entre autre un problème de conception (voir schéma DIA), des classes ont été créées mais elles ne sont pas encore utilisables.
Les setters de Graphe ont été rendus privés pour une meilleure encapsulation.
Les méthodes add de EnsembleArc et ajouteArc,supprArc de Graphe peuvent maintenant prendre en arguments deux sommets(représentant un arc).
Les algorithmes de fermeture transitives (Puissace de graphes,RoyWarshall) ont été rajoutés.
La classe AlgorithmeGraphe a été scindée en plusieurs autre classes (Parcours et FermetureTransitive).
Des tests unitaires ont été ajoutés au fur et à mesure de l'ajout de classes/méthodes

V 0.6.1

Les coûts ont enfin été ajoutés:
- Une class Cout comprenant un flottant a été créée.
- La classe ArcValue hérite de Arc et comporte un attribut Cout.
- EnsembleArc et EnsembleArcValue descendent de AbstractEnsembleArc, en conséquence EnsembleArcValue ne peut contenir que des ArcValue et pas d'Arc.
- Graphe et GrapheValue descendent de AbstractGraphe.

Les algorithmes de graphes ont été décomposés en plusieurs sous parties:
- FermetureTransitive
- Parcours
- DetectionCircuit
- AntiTransitif

V 0.6.2

La manière de noter les versions a été modifiée.
Modification du nommage des packages.
Les interfaces ont été rajoutées, les classes Graphe doivent implémenter les interfaces d'EnsembleSommet et de l'EnsembleArc correspondant.(exemple : une liste de prédécesseurs doit pouvoir être obtenue à partir du Graphe ou de l'EnsembleArc au choix).
Les différentes classes d'Ensemble et de Graphe ont donc été mise à jour en conséquence.
Les test unitaires ont été mis à jours.

V 0.7

La classe CFC ont été ajoutées ainsi que la classe CalculCFC comprenant l'algorithme de Foulkes.
La Méthode getCout pour les grapheValue et les ensemblesValue a été modifiée.
Plusieurs méthode clone ont été ajoutées et/ou modifiées afin de mieux encapsuler les différents éléments et empêcher toute modification de l'exterieur.
Les test unitaires ont été renforcés.
La classe plusCC a été ajoutée et l'algorithme de Dijktra a été implémenté.

V 0.8

La classe TableauPlusCC a été ajoutées (elle contient les tableaux d et pred necessaires au calcul du plus court chemin).
Les algorithmes de plus court chemin ont été implémentés (il ne reste plus que Ford).
Tous les algorithmes du cours (hors feuille annexe) ont été ajoutés.

Remarque : 
- La méthode firstSommet de EnsembleSommet est un peu crade donc est à modifier dès que possible.
- Essayer de voir si on peut trouver une manière de réexploiter facilement des Algorithme/Méthodes (exemple : ne pas avoir à écrire tout RoyWarshall lors de la fermeture anti-Transitive) afin de rendre le projet plus facilement maléable.
- Implémenter une méthode union pour les Ensemble et réussir à l'ajouter à l'interface sans poser de problèmes pour les Graphes.

Prochaine étape : Faire une mise à jour des différents ensembles pour qu'ils contiennent un objet de type HashSet plutôt que d'être des descendant de hashSet (pour une meilleure encapsulation).