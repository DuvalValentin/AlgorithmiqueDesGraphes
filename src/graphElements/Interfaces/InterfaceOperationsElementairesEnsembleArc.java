package graphElements.Interfaces;

public interface InterfaceOperationsElementairesEnsembleArc<S,A extends InterfaceArc<S>> 
{
	//Liste des successeurs et prédessesseurs
	InterfaceEnsembleSommet<S> listSucc(InterfaceSommet<S> sommet);//Liste des successeurs
	InterfaceEnsembleSommet<S> listPred(InterfaceSommet<S> sommet);//Liste des prédessesseurs
	//Test de l'existence d'arcs et de boucles
	//Un arc A existe si il existe un arc B dans l'ensemble tel que A et B ont le même départ et arrivée
	boolean existeArc(InterfaceArc<S> arc);//Rend true si l'arc existe
	boolean existeArc(InterfaceSommet<S> arrivee, InterfaceSommet<S> depart);//Pareil mais avec deux sommets en entrée
	boolean existeBoucle();//Rend vrai si il existe un arc tel que depart==arrivee
	boolean existeBoucle(InterfaceSommet<S> sommet);//Rend vrai si il existe un arc (sommet,sommet)
	//Ajout et suppression d'arcs
	//L'arc peut être ajouté seulement si il n'existe pas déja, l'arc est supprimé seulement si il existe
	void ajouteArc(A arc);//Ajoute l'arc 
	void supprArc(A arc);//Supprime l'arc
	void supprArc(InterfaceSommet<S>depart, InterfaceSommet<S>arrivee);//Pareil mais avec deux sommets en entrée	
	//TODO créer une fonction pour savoir si un ensemble est un sous-ensemble de this 
}