package graphElements.Elements;

import java.util.HashSet;
import graphElements.Abstract.AbstractEnsemble;
import graphElements.Interfaces.InterfaceEnsemble;
import graphElements.Interfaces.InterfaceOperationsElementairesEnsembleSommet;

public class EnsembleSommet<S> extends AbstractEnsemble<Sommet<S>> implements InterfaceOperationsElementairesEnsembleSommet<S>, InterfaceEnsemble<Sommet<S>>
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
		//TODO améliorer la méthode fisrtSommet() de Sommet
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
	
	public static <S> EnsembleSommet<S> union(EnsembleSommet<S> Ensemble1,EnsembleSommet<S> Ensemble2)
	{
		EnsembleSommet<S> union = new EnsembleSommet<S>(Ensemble1);
		for(Sommet<S> sommet : Ensemble2.getEnsemble())
		{
			union.ajouteSommet(sommet);
		}
		return union;
	}
	
	public static <S > EnsembleSommet<S> intersection(EnsembleSommet<S> Ensemble1,EnsembleSommet<S> Ensemble2)
	{
		EnsembleSommet<S> intersection = new EnsembleSommet<S>(Ensemble1);
		for(Sommet<S> sommet : Ensemble1.getEnsemble())
		{
			if(!Ensemble2.existeSommet(sommet))
			{
				intersection.supprSommet(sommet);
			}
		}
		return intersection;
	}
	
}
