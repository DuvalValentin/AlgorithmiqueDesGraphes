package graphElements.Elements;

import java.util.HashSet;

import factory.Factory;
import graphElements.Abstract.AbstractEnsemble;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceSommet;

public class EnsembleSommet<S> extends AbstractEnsemble<InterfaceSommet<S>> implements InterfaceEnsembleSommet<S>
{
	public EnsembleSommet()
	{
		super();
	}
	public EnsembleSommet(InterfaceEnsembleSommet<S> Ensemble)
	{
		for (InterfaceSommet<S> sommet : Ensemble.getEnsemble())
		{
			ajouteElement(Factory.sommet(sommet));
		}
	}
	
	@Override
	public InterfaceSommet<S> pickSommet()
	{
		//TODO améliorer la méthode pickSommet() de Sommet
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
		return new HashSet<InterfaceSommet<S>>(ensemble);
	}
}