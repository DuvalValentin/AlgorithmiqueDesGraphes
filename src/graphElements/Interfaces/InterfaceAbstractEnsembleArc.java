package graphElements.Interfaces;

import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
import graphElements.Elements.Arc;

public interface InterfaceAbstractEnsembleArc<S, A extends Arc<S>> extends InterfaceAbstractEnsemble
{
	//Liste des successeurs et prédessesseurs
	EnsembleSommet<S> listSucc(Sommet<S> sommet);
	EnsembleSommet<S> listPred(Sommet<S> sommet);
	//Test de l'existence d'arcs et de boucles
	boolean existeArc(Arc<S> arc);
	boolean existeArc(Sommet<S> arrivee, Sommet<S> depart);
	boolean existeBoucle();
	boolean existeBoucle(Sommet<S> sommet);
	//Ajout et suppression d'éléments
	void ajouteArc(A arc);
	void supprArc(A arc);
	
//TODO créer une fonction pour savoir si un ensemble est un sous-ensemble de this 
}