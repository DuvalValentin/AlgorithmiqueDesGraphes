package graphElements.Interfaces;

import java.util.HashSet;

import graphElements.Abstract.AbstractEnsemble;

public interface InterfaceAbstractEnsemble<E>
{
	//Cette Interface est pour des objets encapsulant un HashSet appellé ensemble.
	//Getter
	HashSet<E> getEnsemble();//Donne une copie de l'ensemble
	//Autres
	boolean isEmpty();//Rend true si l'ensemble est vide
	void clear();//Vide l'ensemble
	//Union intersection
	void union(AbstractEnsemble<E> Ensemble);//Union entre deux ensembles
	void intersection(AbstractEnsemble<E> Ensemble);//Intersection entre deux ensembles
}
