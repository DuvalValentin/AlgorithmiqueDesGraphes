package graphelements.elements;

import java.util.HashSet;
import factory.Factory;
import graphelements.abstracts.AbstractEnsemble;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.Sommet;

public class EnsembleSommetImpl<S>extends AbstractEnsemble<Sommet<S>> implements EnsembleSommet<S>
{
	public EnsembleSommetImpl()
	{
		super();
	}
	public EnsembleSommetImpl(EnsembleSommet<S> ensemble)
	{
		for(Sommet<S> sommet : ensemble.getEnsemble())
		{
			ajouteElement(Factory.sommet(sommet));
		}
	}
	@Override
	public Sommet<S> pickSommet()
	{
		// FIXME améliorer la méthode pickSommet() de Sommet
		@SuppressWarnings("unchecked")
		Sommet<S>[] tab=new SommetImpl[ensemble.size()];
		return ensemble.toArray(tab)[0];
	}
	@Override
	public boolean existeSommet(Sommet<S> sommet)
	{
		return ensemble.contains(sommet);
	}
	@Override
	public void ajouteElement(Sommet<S> sommet)
	{
		ensemble.add(Factory.sommet(sommet));
	}
	@Override
	public void ajouteSommet(S id)
	{
		ensemble.add(Factory.sommet(id));
	}
	@Override
	public void supprElement(Sommet<S> sommet)
	{
		ensemble.remove(sommet);
	}
	@Override
	public HashSet<Sommet<S>> getEnsemble()
	{
		return new HashSet<>(ensemble);
	}
}