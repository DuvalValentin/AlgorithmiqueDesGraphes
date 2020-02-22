package graphelements.interfaces;

import java.util.Optional;

public interface OperationsElementairesEnsembleArcValue<S>extends OperationsElementairesEnsembleArc<S>
{
	// getter
	Optional<Cout> getCout(Sommet<S> depart, Sommet<S> arrivee);// Rend le cout
																															// d'un arc, null
																															// si l'arc
																															// n'existe pas
	// setter
	boolean setValeur(Sommet<S> depart, Sommet<S> arrivee, Cout cout);// Modifie
																																		// la valeur
																																		// du cout
																																		// d'un arc
	// ajout d'élément
	// Ajout l'arc
}
