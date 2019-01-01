package graphElements.Interfaces;

import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;

public interface InterfaceAbstractGraphe<S, A extends Arc<S>> extends InterfaceEnsembleSommet<S>,InterfaceAbstractEnsembleArc<S,A>
{
	//Getters et Setters
	EnsembleSommet<S> getX();
	AbstractEnsembleArc<S, A> getGamma();
	//Vérification des éléments
	boolean ajoutableArc(Arc<S> arc);
	boolean correctGamma(AbstractEnsembleArc<S, A> gamma);
	boolean isEmpty();
	//Liste des entrées sorties
	EnsembleSommet<S> pointsEntree();
	EnsembleSommet<S> pointsSortie();
	//Union
	void union(InterfaceAbstractGraphe<S, A> G);

}