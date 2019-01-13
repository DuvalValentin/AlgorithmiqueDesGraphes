package graphElements.Interfaces;

import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;

public interface InterfaceAbstractGraphe<S, A extends Arc<S>> extends InterfaceEnsembleSommet<S>,InterfaceAbstractEnsembleArc<S,A>
{
	//Un graphe possède un attribut InterfaceEnsembleSommet<S> nommé X et un attribut InterfaceAbstractEnsembleArc nommé Gamma
	//Les méthode provenant de InterfaceEnsembleSommet et de InterfaceAbstractEnsembleArc devront être réécrite sous la forme (X.methode())
	//Getters et Setters
	EnsembleSommet<S> getX();//Renvoie une copie de X
	AbstractEnsembleArc<S, A> getGamma();//Renvoie une copie de Gamma
	//Vérification des éléments
	boolean ajoutableArc(A arc);//Rend true si l'arc n'existe pas dans Gamma et que son départ et son arrviée existent dans X
	boolean correctGamma(AbstractEnsembleArc<S, A> gamma);//Rend true si tout les Sommet contenus dans les Arc de Gamma sont présents dans X
	boolean isEmpty();//Rend true si X et Gamma sont vide (PS : normalement, si X est vide alors Gamma est vide)
	//Liste des entrées sorties
	EnsembleSommet<S> pointsEntree();//Rend la liste des points d'entrée
	EnsembleSommet<S> pointsSortie();//Rend une liste des points de sortie
	//Union
	void union(InterfaceAbstractGraphe<S, A> G);//Union entre deux graphes=> union entre les deux X et union entre les deux Gamma

}