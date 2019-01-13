package graphElements.Elements;

import java.util.HashSet;

import graphElements.Abstract.AbstractEnsemble;
import graphElements.Interfaces.InterfaceEnsembleSommet;

public class EnsembleSommet<S> extends AbstractEnsemble<Sommet<S>> implements InterfaceEnsembleSommet<S>
{
	public EnsembleSommet()
	{
		super();
	}
	public EnsembleSommet(EnsembleSommet<S> Ensemble)
	{
		for (Sommet<S> sommet : Ensemble.ensemble)
		{
			ajouteSommet(new Sommet<S>(sommet));
		}
	}
	
	@Override
	public Sommet<S> firstSommet()
	{
		//TODO à améliorer si possible
		@SuppressWarnings("unchecked")
		Sommet<S>[] tab=new Sommet[ensemble.size()]; 
		return ensemble.toArray(tab)[0];
	}
	
	@Override
	public boolean existeSommet (Sommet<S> sommet)
	{
		return ensemble.contains(sommet);
	}
	@Override
	public void ajouteSommet(Sommet<S> sommet)
	{
		ensemble.add(new Sommet<S>(sommet));
	}
	@Override
	public void supprSommet(Sommet<S> sommet)
	{
		ensemble.remove(sommet);
	}

	@Override
	public HashSet<Sommet<S>> getEnsemble()
	{
		return new HashSet<Sommet<S>>(ensemble);
	}
	@Override
	public void union(AbstractEnsemble<Sommet<S>> Ensemble)
	{
		for(Sommet<S> sommet : Ensemble.getEnsemble())
		{
			ajouteSommet(sommet);
		}
	}
	@Override
	public void intersection(AbstractEnsemble<Sommet<S>> Ensemble)
	{
		for(Sommet<S> sommet : ensemble)
		{
			if(!Ensemble.getEnsemble().contains(sommet))//TODO on veut faire un existeSommet ici
			{
				this.supprSommet(sommet);
			}
		}
	}
}
