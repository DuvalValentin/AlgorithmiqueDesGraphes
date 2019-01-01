package graphElements.Interfaces;

import graphElements.Elements.Arc;
import graphElements.Elements.Sommet;

public interface InterfaceEnsembleArc<S> extends InterfaceAbstractEnsembleArc<S,Arc<S>>
{
	void ajouteArc(Sommet<S>depart,Sommet<S>arrivee);
	void supprArc(Sommet<S>depart, Sommet<S>arrivee);
}
