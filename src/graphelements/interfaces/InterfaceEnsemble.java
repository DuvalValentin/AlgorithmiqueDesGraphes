package graphelements.interfaces;

import java.util.HashSet;

import factory.Factory;

public interface InterfaceEnsemble<E>
{
	//Cette Interface est pour des objets encapsulant un HashSet appellé ensemble.
	//Getter
	HashSet<E> getEnsemble();//Donne une copie de l'ensemble
	//Autres
	boolean isEmpty();//Rend true si l'ensemble est vide
	void clear();//Vide l'ensemble
	void supprElement(E element);
	void ajouteElement(E element);
	boolean contient(InterfaceEnsemble<E> ensemble);
	
	static <E> InterfaceEnsemble<E> union(InterfaceEnsemble<E> ensemble1,InterfaceEnsemble<E> ensemble2)
	{
		InterfaceEnsemble<E> union = Factory.ensemble(ensemble1);
		for(E element : ensemble2.getEnsemble())
		{
			if(!ensemble1.getEnsemble().contains(element))
			{
				union.ajouteElement(element);
			}
		}
		return union;
	}
	static <E> InterfaceEnsemble<E> intersection(InterfaceEnsemble<E> ensemble1,InterfaceEnsemble<E> ensemble2)
	{
		InterfaceEnsemble<E> intersection = Factory.ensemble(ensemble1);
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