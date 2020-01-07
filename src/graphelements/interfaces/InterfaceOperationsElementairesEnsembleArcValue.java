package graphelements.interfaces;

import java.util.Optional;

public interface InterfaceOperationsElementairesEnsembleArcValue<S> extends InterfaceOperationsElementairesEnsembleArc<S>
{
	//getter
	Optional<InterfaceCout> getCout(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee);//Rend le cout d'un arc, null si l'arc n'existe pas
	//setter
	boolean setValeur(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout);//Modifie la valeur du cout d'un arc
	//ajout d'élément
	//Ajout l'arc
}
