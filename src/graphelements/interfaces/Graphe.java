package graphelements.interfaces;

import factory.Factory;

public interface Graphe<S,A extends Arc<S>>extends OperationsElementairesEnsembleSommet<S>, OperationsElementairesEnsembleArc<S>
{
	// Un graphe possède un attribut InterfaceEnsembleSommet<S> nommé X et un
	// attribut InterfaceAbstractEnsembleArc nommé Gamma
	// Les méthode provenant de InterfaceEnsembleSommet et de
	// InterfaceAbstractEnsembleArc devront être réécrite sous la forme
	// (X.methode())
	// Getters et Setters
	EnsembleSommet<S> getEnsembleSommet();// Renvoie une copie de X
	EnsembleArc<S,A> getGamma();// Renvoie une copie de Gamma
	// Vérification des éléments
	boolean ajoutableArc(A arc);// Rend true si l'arc n'existe pas dans Gamma et
															// que son départ et son arrviée existent dans X
	boolean correctGamma(EnsembleArc<S,A> gamma);// Rend true si tout les Sommet
																								// contenus dans les Arc de
																								// Gamma sont présents dans X
	boolean isEmpty();// Rend true si X et Gamma sont vide (PS : normalement, si X
										// est vide alors Gamma est vide)
	// Liste des entrées sorties
	EnsembleSommet<S> pointsEntree();// Rend la liste des points d'entrée
	EnsembleSommet<S> pointsSortie();// Rend une liste des points de sortie
	static <S,A extends Arc<S>> Graphe<S,A> union(Graphe<S,A> graphe1, Graphe<S,A> graphe2)
	{
		Graphe<S,A> union=Factory.graphe(graphe1);
		for(Sommet<S> sommet : graphe2.getEnsembleSommet().getEnsemble())
		{
			union.ajouteSommet(sommet);
		}
		for(A arc : graphe2.getGamma().getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}
	void ajouteSommet(Sommet<S> sommet);
	void supprSommet(Sommet<S> sommet);
	void ajouteArc(A arc);// Ajoute l'arc
	void supprArc(A arc);// Supprime l'arc
	void supprArc(Sommet<S> depart, Sommet<S> arrivee);// Pareil mais avec deux
																											// sommets en entrée
}