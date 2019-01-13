package graphElements.Interfaces;

import graphElements.Elements.Arc;
import graphElements.Elements.Sommet;

public interface InterfaceEnsembleArc<S> extends InterfaceAbstractEnsembleArc<S,Arc<S>>
{
	//Ajout d'élément
	void ajouteArc(Sommet<S>depart,Sommet<S>arrivee);//Ajoute l'arc
}
