package graphElements.Interfaces;

import graphElements.Elements.Sommet;

public interface InterfaceEnsembleSommet<S> extends InterfaceAbstractEnsemble
{
	//Obtenir un sommet
	Sommet<S> firstSommet();
	//Test d'existence d'un sommet
	boolean existSommet(Sommet<S> sommet);
	//Ajout et suppression de sommet
	void ajouteSommet(Sommet<S> sommet);
	void supprSommet(Sommet<S> sommet);
}
