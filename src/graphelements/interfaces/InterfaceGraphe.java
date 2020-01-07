package graphelements.interfaces;

import factory.Factory;

public interface InterfaceGraphe<S,A extends InterfaceArc<S>> extends InterfaceOperationsElementairesEnsembleSommet<S>,InterfaceOperationsElementairesEnsembleArc<S>
{
	//Un graphe possède un attribut InterfaceEnsembleSommet<S> nommé X et un attribut InterfaceAbstractEnsembleArc nommé Gamma
	//Les méthode provenant de InterfaceEnsembleSommet et de InterfaceAbstractEnsembleArc devront être réécrite sous la forme (X.methode())
	//Getters et Setters
	InterfaceEnsembleSommet<S> getEnsembleSommet();//Renvoie une copie de X
	InterfaceEnsembleArc<S,A> getGamma();//Renvoie une copie de Gamma
	
	//Vérification des éléments
	boolean ajoutableArc(A arc);//Rend true si l'arc n'existe pas dans Gamma et que son départ et son arrviée existent dans X
	boolean correctGamma(InterfaceEnsembleArc<S,A> gamma);//Rend true si tout les Sommet contenus dans les Arc de Gamma sont présents dans X
	boolean isEmpty();//Rend true si X et Gamma sont vide (PS : normalement, si X est vide alors Gamma est vide)
	//Liste des entrées sorties
	InterfaceEnsembleSommet<S> pointsEntree();//Rend la liste des points d'entrée
	InterfaceEnsembleSommet<S> pointsSortie();//Rend une liste des points de sortie
	static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> union(InterfaceGraphe<S,A> graphe1,InterfaceGraphe<S,A> graphe2)
	{
		InterfaceGraphe<S,A> union = Factory.graphe(graphe1);
		for(InterfaceSommet<S> sommet : graphe2.getEnsembleSommet().getEnsemble())
		{
			union.ajouteSommet(sommet);
		}
		for(A arc : graphe2.getGamma().getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}
	void ajouteSommet(InterfaceSommet<S> sommet);
	void supprSommet(InterfaceSommet<S> sommet);
	void ajouteArc(A arc);//Ajoute l'arc 
	void supprArc(A arc);//Supprime l'arc
	void supprArc(InterfaceSommet<S>depart, InterfaceSommet<S>arrivee);//Pareil mais avec deux sommets en entrée
}