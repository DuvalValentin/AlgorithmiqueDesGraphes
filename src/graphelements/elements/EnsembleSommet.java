package graphelements.elements;

import java.util.HashSet;

import factory.Factory;
import graphelements.abstracts.AbstractEnsemble;
import graphelements.interfaces.InterfaceEnsembleSommet;
import graphelements.interfaces.InterfaceSommet;

public class EnsembleSommet<S> extends AbstractEnsemble<InterfaceSommet<S>> implements InterfaceEnsembleSommet<S>
{
	public EnsembleSommet()
	{
		super();
	}
	public EnsembleSommet(InterfaceEnsembleSommet<S> ensemble)
	{
		for (InterfaceSommet<S> sommet : ensemble.getEnsemble())
		{
			ajouteElement(Factory.sommet(sommet));
		}
	}
	
	@Override
	public InterfaceSommet<S> pickSommet()
	{
		//FIXME améliorer la méthode pickSommet() de Sommet
		@SuppressWarnings("unchecked")
		InterfaceSommet<S>[] tab=new Sommet[ensemble.size()];
		return ensemble.toArray(tab)[0];
	}
	
	@Override
	public boolean existeSommet (InterfaceSommet<S> sommet)
	{
		return ensemble.contains(sommet);
	}
	@Override
	public void ajouteElement(InterfaceSommet<S> sommet)
	{
		ensemble.add(Factory.sommet(sommet));
	}
	@Override
	public void ajouteSommet(S id)
	{
		ensemble.add(Factory.sommet(id));
	}
	@Override
	public void supprElement(InterfaceSommet<S> sommet)
	{
		ensemble.remove(sommet);
	}

	@Override
	public HashSet<InterfaceSommet<S>> getEnsemble()
	{
		return new HashSet<>(ensemble);
	}
}