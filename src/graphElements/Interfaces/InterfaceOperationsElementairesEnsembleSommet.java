package graphElements.Interfaces;

import graphElements.Elements.Sommet;

public interface InterfaceOperationsElementairesEnsembleSommet<S> 
{
	//Obtenir un sommet
	Sommet<S> firstSommet();//Rend une copie du "premier" sommet de l'ensemble
	//Test d'existence d'un sommet
	//Un sommet A existe si il y a un sommet B dans l'ensemble tel que A equals B
	boolean existeSommet(Sommet<S> sommet);//Rend true si le sommet existe
	//Ajout et suppression de sommet
	void ajouteSommet(Sommet<S> sommet);//Ajoute le sommet si il n'existe pas
	void supprSommet(Sommet<S> sommet);//Supprime le sommet si il existe
}
