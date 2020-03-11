package graphelements.interfaces;

import java.util.Optional;

public interface OperationsElementairesEnsembleArcValue<S>extends OperationsElementairesEnsembleArc<S>
{
	// getter
	Optional<Float> getCout(Sommet<S> depart, Sommet<S> arrivee);// Rend le cout d'un arc, null si l'arc n'existe pas
	// setter
	boolean setValeur(Sommet<S> depart, Sommet<S> arrivee, Float cout);// Modifie la valeurdu cout d'un arc
	// ajout d'élément
	// Ajout l'arc
}
