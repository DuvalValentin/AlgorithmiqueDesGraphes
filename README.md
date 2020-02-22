# AlgorithmiqueDesGraphes
Version 0.11

Le but de ces packages est de disposer d'un ensemble d'outils facile à utiliser permettant de manipuler des Graphes.

L'objectif final étant de pouvoir effectuer un maximum  d'algorithmes sur les graphes.

V 0.1
=====
Actuellement ce package ne dispose que d'objets basiques : 
* Sommet : contient un attribut Id qui peut-être n'importe quel objet.
* Arc : comporte 2 sommets.
* EnsembleSommet : HashSet de Sommets.
* EnsembleArc : HashSet d'Arc.
* Graphe : comporte un EnsembleSommet et un EnsembleArc formant ainsi un graphe.

Pour le moment il n'y a quE des setters et des getters définis ainsi que les méthodes toString qui ont été redéfinies.

V 0.2
=====

* Les méthodes equals et HashCode ont été redéfinies pour les sommets et les arcs, ainsi deux sommet ayant le même objet en Id ne peuvent être ajoutés dans un même EnsembleSommet, même chose pour deux arcs ayant le mêmes Sommets de depart et d'arrivée.
* De même ces méthodes ont été modifiées pour les Graphes afin que les Graphes aient des hashCode corrects et qu'ils puissent être comparés à d'autre Graphes.

V 0.3
=====

* Plus aucun warning signalés dans le projet (@SuppressWarnings("unchecked") pour les méthode incriminées)
* Impémentation des méthodes dites élémentaires ( liste de successeur/prédeccésseur ; existence des sommets/arcs/boucles ; ajout/suppression d'arcs dans un graphe.
* Avec ces nouvelles méthodes les algorithmes de parcours DFS et WFS ont pu être crées dans une nouvelle classe : AlgorithmeGraphe (classe qui permetra d'appeller des algoritmes de manière statique, les méthodes algorithme prennent des graphes en paramètre.)
* La classe Test a été renommée en ExempleUtilisation

V 0.4
=====

* Des tests unitaires ont été réalisés avec JUnit pour chacune des classes et ce pour la plupart des méthodes.
* Les méthodes ajoutSommet et supprSommet ont été ajoutées.
* X et Gamma dans le Graphe ont été mieux encapsulés pour empêcher des modifications sur ses deux ensembles sans passer par le Graphe, les utilisateurs n'ont (normalement) plus accès aux graphes.
* La plupart des this on été supprimés car ils n'étaient pas utiles.

V 0.5
=====

* Ajouter les coût a été moins facile que prévu, il y a entre autre un problème de conception (voir schéma DIA), des classes ont été créées mais elles ne sont pas encore utilisables.
* Les setters de Graphe ont été rendus privés pour une meilleure encapsulation.
* Les méthodes add de EnsembleArc et ajouteArc,supprArc de Graphe peuvent maintenant prendre en arguments deux sommets(représentant un arc).
* Les algorithmes de fermeture transitives (Puissace de graphes,RoyWarshall) ont été rajoutés.
* La classe AlgorithmeGraphe a été scindée en plusieurs autre classes (Parcours et FermetureTransitive).
* Des tests unitaires ont été ajoutés au fur et à mesure de l'ajout de classes/méthodes

V 0.6.1
=======

* Les coûts ont enfin été ajoutés
    * Une class Cout comprenant un flottant a été créée.
    * La classe ArcValue hérite de Arc et comporte un attribut Cout.
    * EnsembleArc et EnsembleArcValue descendent de AbstractEnsembleArc, en conséquence EnsembleArcValue ne peut contenir que des ArcValue et pas d'Arc.
    * Graphe et GrapheValue descendent de AbstractGraphe.
* Les algorithmes de graphes ont été décomposés en plusieurs sous parties:
    * FermetureTransitive
    * Parcours
    * DetectionCircuit
    * AntiTransitif

V 0.6.2
=======

* La manière de noter les versions a été modifiée.
* Modification du nommage des packages.
* Les interfaces ont été rajoutées, les classes Graphe doivent implémenter les interfaces d'EnsembleSommet et de l'EnsembleArc correspondant.(exemple : une liste de prédécesseurs doit pouvoir être obtenue à partir du Graphe ou de l'EnsembleArc au choix).
* Les différentes classes d'Ensemble et de Graphe ont donc été mise à jour en conséquence.
* Les test unitaires ont été mis à jours.

V 0.7
=====

* La classe CFC ont été ajoutées ainsi que la classe CalculCFC comprenant l'algorithme de Foulkes.
* La Méthode getCout pour les grapheValue et les ensemblesValue a été modifiée.
* Plusieurs méthode clone ont été ajoutées et/ou modifiées afin de mieux encapsuler les différents éléments et empêcher toute modification de l'exterieur.
* Les test unitaires ont été renforcés.
* La classe plusCC a été ajoutée et l'algorithme de Dijktra a été implémenté.

V 0.8
=====

* La classe TableauPlusCC a été ajoutées (elle contient les tableaux d et pred necessaires au calcul du plus court chemin).
* Les algorithmes de plus court chemin ont été implémentés (il ne reste plus que Ford).
* Tous les algorithmes du cours (hors feuille annexe) ont été ajoutés.

V 0.9
=====

* Les Ensembles ont été mis à jour, désormais ce ne sont plus des HashSet mais des objet contenant un HashSet, le reste a donc été modifié en conséquences.
* Toutes les méthodes clones ont été suprimées. (remplacé par les new Object(object)).
* Des tests unitaires concerant l'encapsulation ont été rajoutés.
* L'Algorithme de Tarjan a été implémenté.
* Modification de existeArc pour mieux coller avec les ensemble d'arc valués.
* Création de la méthode meme arc permetant de savoir si deux arcs ont un même départ et une même arrivée (indépendament du reste).
* La partie remarque a été décalée dans le fichier Question importantes du moment.
* Beaucoup de commentaires rajoutés dans les interfaces.

V 0.10.1
========

* Union et intesection sont désormais statiques mais pas encore comme je le souhaiterais, l'alogorithme de Ford a été implémenté.
* Découverte du coverage, les tests couvrents désormais 100% les packages abstract et elements (il reste encore les algorithmes à couvrir correctement).

V 0.11
======
* Une classe Factory a été créée, le code a pû donc être modifié afin de respecter l'inversion de dépendances (il reste encore les méthodes d'union à gérer),
* L'union et l'intersection sont correctes mais je ne suis pas encore satisfait.

V 0.11.1
======
* Les méthodes statiques (union et intersection) ont été déplacées dans les Interfaces => il y a donc des méthodes implémentées dans les Interfaces mais désormais le code respecte entièrement le principe d'inversion de dépendances
* La méthode getCout de EnsembleArcValue rend maintenant un objet de type Optional<InterfaceCout>

V 0.11.2
======
* Passage de JUnit4 à JUnit5 Jupiter
* Passage de projet Java "simple" à projet Maven

V 0.12
======
* Ajouts de nouveaux outils tels que jacoco et sonar
* Gros refactoring pour respecter au mieux les bonnes pratiques (rennomages, changement de certains types..)
* 2 bugs détecté via sonar corrigé

V 0.13
======
* Changement des conventions de nommage (plus de Interface mais il y a des Impl)
* Coverage du code 100% pour les instructions (mais pas toutes les branches)
* Mise en place d'un formateur et application de ce formateur sur tout le code

Encore à faire :
----------------
* Revoir les TODO présents dans le code.
* Créer une fonction pour savoir si un Ensemble est un sous ensemble d'un autre Ensemble (utiliser Union en statique).
* Les algorithmes CFC doivent rendre un graphe d'ensemble de graphe.
* Répondre aux questions du moment et faire leurs implémentations.
* Commencer les algorithmes d'ordonnancement.
* Respecter l'architecture Maven Classique.
* Avoir un "Sonar" le plus propre que possible.
* Atteindre 100% de coverage.