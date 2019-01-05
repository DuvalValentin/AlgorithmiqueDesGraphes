package graphElements.Interfaces;

import graphElements.Elements.ArcValue;
import graphElements.Elements.Cout;
import graphElements.Elements.Sommet;

public interface InterfaceEnsembleArcValue<S> extends InterfaceAbstractEnsembleArc<S,ArcValue<S>>
{
	void ajouteArc(Sommet<S> depart, Sommet<S> arrivee, Cout cout);
	//boolean getCout(Sommet<S> depart, Sommet<S> arrivee, Cout result);
	Cout getCout(Sommet<S> depart, Sommet<S> arrivee);
	boolean setCout(Sommet<S> depart, Sommet<S> arrivee, Cout cout);
}