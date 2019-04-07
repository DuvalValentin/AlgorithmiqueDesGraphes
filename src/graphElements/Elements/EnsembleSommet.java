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
			ajouteSommet(Factory.sommet(sommet));
		}
	}
	
	@Override
	public InterfaceSommet<S> pickSommet()
	{
		//TODO améliorer la méthode fisrtSommet() de Sommet
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
	public void ajouteSommet(InterfaceSommet<S> sommet)
	{
		ensemble.add(Factory.sommet(sommet));
	}
	@Override
	public void supprSommet(InterfaceSommet<S> sommet)
	{
		ensemble.remove(sommet);
	}

	@Override
	public HashSet<InterfaceSommet<S>> getEnsemble()
	{
		return new HashSet<InterfaceSommet<S>>(ensemble);
	}
}