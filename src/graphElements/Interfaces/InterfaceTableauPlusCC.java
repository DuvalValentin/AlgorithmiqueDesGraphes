package graphElements.Interfaces;

import java.util.HashMap;

public interface InterfaceTableauPlusCC<S>
{
	//Un TableauPlusCC doit contenir : 
	//principal : le Sommet principal à partir duquel on effectue les calculs 
	//d : à chaque Sommet est associé un Cout (le coût pour aller du Sommet principal à ce Sommet
	//pred : à chaque Sommet est associé un autre Sommet : celui par lequel il faut passer pour avoir le plus court chemin
	//Setters
	HashMap<InterfaceSommet<S>,InterfaceCout> getD();//Renvoie une copie de d
	HashMap<InterfaceSommet<S>,InterfaceSommet<S>> getPred();//Renvoie une copie de pred
	InterfaceSommet<S> getPrincipal();//Renvoie une copie de principal
	InterfaceCout getDistance(InterfaceSommet<S> sommet);//Renvoie la distance (la valeur de d pour un sommet donné) 
	InterfaceSommet<S> getPredecesseur(InterfaceSommet<S> sommet);//Renvoie le prédcésseur par lequel on doit passer pour faire le plusCC (la valeur de pred pour un sommet donné)
	//Modification d'éléments
	void initSommet(InterfaceSommet<S> sommet);//Initialise les valeurs d'un sommet dans d (null) et dans pred (principal)
	void modifDistance(InterfaceSommet<S> sommet, InterfaceCout cout);//Modifie la distance pour un sommet donné
	void modifPredecesseur(InterfaceSommet<S> sommet,InterfaceSommet<S> predessesseur);//Modiie le prédecesseur dans pred pour un sommet donné
}
