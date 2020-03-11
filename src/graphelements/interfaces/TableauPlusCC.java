package graphelements.interfaces;

import java.util.HashMap;

public interface TableauPlusCC<S>
{
	// Un TableauPlusCC doit contenir :
	// principal : le Sommet principal à partir duquel on effectue les calculs
	// d : à chaque Sommet est associé un Cout (le coût pour aller du Sommet
	// principal à ce Sommet
	// pred : à chaque Sommet est associé un autre Sommet : celui par lequel il
	// faut passer pour avoir le plus court chemin
	// Setters
	HashMap<Sommet<S>,Float> getD();// Renvoie une copie de d
	HashMap<Sommet<S>,Sommet<S>> getPred();// Renvoie une copie de pred
	Sommet<S> getPrincipal();// Renvoie une copie de principal
	Float getDistance(Sommet<S> sommet);// Renvoie la distance (la valeur de d
																			// pour
																			// un sommet donné)
	Sommet<S> getPredecesseur(Sommet<S> sommet);// Renvoie le prédcésseur par
																							// lequel on doit passer pour
																							// faire le plusCC (la valeur de
																							// pred pour un sommet donné)
																							// Modification d'éléments
	void initSommet(Sommet<S> sommet);// Initialise les valeurs d'un sommet dans d
																		// (null) et dans pred (principal)
	void modifDistance(Sommet<S> sommet, Float cout);// Modifie la distance pour
																										// un
																										// sommet donné
	void modifPredecesseur(Sommet<S> sommet, Sommet<S> predessesseur);// Modiie le
																																		// prédecesseur
																																		// dans pred
																																		// pour un
																																		// sommet
																																		// donné
}
