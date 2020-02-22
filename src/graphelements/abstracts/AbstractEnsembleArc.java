package graphelements.abstracts;

import factory.Factory;
import graphelements.interfaces.Arc;
import graphelements.interfaces.EnsembleArc;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.Sommet;

public abstract class AbstractEnsembleArc<S,A extends Arc<S>>extends AbstractEnsemble<A> implements EnsembleArc<S,A>
{
	// Constructeurs
	public AbstractEnsembleArc()
	{
		super();
	}
	// Test d'existence d'éléments
	@Override
	public boolean existeArc(Arc<S> arc)
	{
		return existeArc(arc.getDepart(),arc.getArrivee());
	}
	@Override
	public boolean existeArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		boolean result=false;
		for(A arc : ensemble)
		{
			if(arc.getDepart().equals(depart)&&arc.getArrivee().equals(arrivee))
			{
				result=true;
				break;
			}
		}
		return result;
	}
	@Override
	public boolean existeBoucle()
	{
		boolean resultat=false;
		for(Arc<S> Arc : ensemble)
		{
			if(Arc.getDepart().equals(Arc.getArrivee()))
			{
				resultat=true;
				break;
			}
		}
		return resultat;
	}
	@Override
	public boolean existeBoucle(Sommet<S> sommet)
	{
		return existeArc(sommet,sommet);
	}
	// Séléction d'éléments
	@Override
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		EnsembleSommet<S> xSucc=Factory.ensembleSommet();
		for(A arc : ensemble)
		{
			if(arc.getDepart().equals(sommet))
			{
				xSucc.ajouteElement(arc.getArrivee());
			}
		}
		return xSucc;
	}
	@Override
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		EnsembleSommet<S> xPred=Factory.ensembleSommet();
		for(A arc : ensemble)
		{
			if(arc.getArrivee().equals(sommet))
			{
				xPred.ajouteElement(arc.getDepart());
			}
		}
		return xPred;
	}
	// Ajouts et suppressions d'éléments ----------------------------------------
	@Override
	public void supprElement(A arc)
	{
		for(A arcE : ensemble)
		{
			if(arcE.memeArc(arc))
			{
				ensemble.remove(arcE);
				break;
			}
		}
	}
	@Override
	public void ajouteElement(A arc)
	{
		if(!existeArc(arc))
		{
			ensemble.add(arc);
		}
	}
}