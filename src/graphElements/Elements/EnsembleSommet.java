package graphElements.Elements;

import graphElements.Abstract.AbstractEnsemble;
import graphElements.Interfaces.InterfaceEnsembleSommet;

public class EnsembleSommet<S> extends AbstractEnsemble<Sommet<S>> implements InterfaceEnsembleSommet<S>
{
	public EnsembleSommet()
	{
		super();
	}
	public EnsembleSommet(EnsembleSommet<S> ensemble)
	{
		for (Sommet<S> sommet : ensemble)
		{
			ajouteSommet(sommet.clone());
		}
	}
	
	private static final long serialVersionUID = 7278825382690341067L;
	
	@Override
	public EnsembleSommet<S> clone ()
	{
		return (EnsembleSommet<S>)super.clone();
	}
	
	@Override
	public Sommet<S> firstSommet()
	{
		@SuppressWarnings("unchecked")
		Sommet<S>[] tab=new Sommet[size()]; 
		return toArray(tab)[0];
	}
	
	@Override
	public boolean existSommet (Sommet<S> sommet)
	{
		return contains(sommet);
	}
	@Override
	public void ajouteSommet(Sommet<S> sommet)
	{
		add(sommet.clone());
	}
	@Override
	public void supprSommet(Sommet<S> sommet)
	{
		remove(sommet);
	}
	//TODO ajouter méthode dans une interface
	public void union(EnsembleSommet<S> ensemble)
	{
		for(Sommet<S> sommet : ensemble)
		{
			ajouteSommet(sommet);
		}
	}
}
