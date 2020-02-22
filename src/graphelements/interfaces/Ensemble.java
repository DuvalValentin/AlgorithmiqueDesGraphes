package graphelements.interfaces;

import java.util.HashSet;
import factory.Factory;

public interface Ensemble<E>
{
	// Cette Interface est pour des objets encapsulant un HashSet appellé
	// ensemble.
	// Getter
	HashSet<E> getEnsemble();// Donne une copie de l'ensemble
	// Autres
	boolean isEmpty();// Rend true si l'ensemble est vide
	void clear();// Vide l'ensemble
	void supprElement(E element);
	void ajouteElement(E element);
	boolean contient(Ensemble<E> ensemble);
	static <E> Ensemble<E> union(Ensemble<E> ensemble1, Ensemble<E> ensemble2)
	{
		Ensemble<E> union=Factory.ensemble(ensemble1);
		for(E element : ensemble2.getEnsemble())
		{
			if(!ensemble1.getEnsemble().contains(element))
			{
				union.ajouteElement(element);
			}
		}
		return union;
	}
	static <E> Ensemble<E> intersection(Ensemble<E> ensemble1, Ensemble<E> ensemble2)
	{
		Ensemble<E> intersection=Factory.ensemble(ensemble1);
		for(E element : ensemble1.getEnsemble())
		{
			if(!ensemble2.getEnsemble().contains(element))
			{
				intersection.supprElement(element);
			}
		}
		return intersection;
	}
}