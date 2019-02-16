package graphElements.Interfaces;

import graphElements.Elements.Cout;
import graphElements.Elements.Sommet;

public interface InterfaceOperationsElementairesEnsembleArcValue<S> extends InterfaceOperationsElementairesEnsembleArc<S,InterfaceArcValue<S>>
{
	//getter
	Cout getCout(Sommet<S> depart, Sommet<S> arrivee);//Rend le cout d'un arc, null si l'arc n'existe pas
	//setter
	boolean setValeur(Sommet<S> depart, Sommet<S> arrivee, Cout cout);//Modifie la valeur du cout d'un arc
	//ajout d'élément
	void ajouteArc(Sommet<S> depart, Sommet<S> arrivee, Cout cout);//Ajout l'arc
}
