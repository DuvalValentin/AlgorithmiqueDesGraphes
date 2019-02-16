package graphElements.Interfaces;

import graphElements.Elements.Sommet;

public interface InterfaceOperationsElementairesEnsembleArcNonValue<S> extends InterfaceOperationsElementairesEnsembleArc<S,InterfaceArc<S>>
{
	//Ajout d'élément
	void ajouteArc(Sommet<S>depart,Sommet<S>arrivee);//Ajoute l'arc
}
