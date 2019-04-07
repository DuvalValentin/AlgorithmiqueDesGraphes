package graphElements.Abstract;

import factory.Factory;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArc;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceSommet;

public abstract class AbstractEnsembleArc<S,A extends InterfaceArc<S>> extends AbstractEnsemble<A> implements InterfaceEnsembleArc<S,A>
{
	//Constructeurs
	public AbstractEnsembleArc()
	{
		super();
	}
	//Test d'existence d'éléments
	@Override
	public boolean existeArc(InterfaceArc<S> arc)
	{
		return existeArc(arc.getDepart(),arc.getArrivee());
	}
	@Override
	public boolean existeArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
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
		for(InterfaceArc<S> Arc : ensemble)
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
	public boolean existeBoucle(InterfaceSommet<S> sommet)
	{
		return existeArc(sommet,sommet);
	}
	//Séléction d'éléments
	@Override
	public InterfaceEnsembleSommet<S> listSucc(InterfaceSommet<S> sommet)
	{
		InterfaceEnsembleSommet<S> XSucc=Factory.ensembleSommet();
		for (A arc : ensemble)
		{
			if (arc.getDepart().equals(sommet))
			{
				XSucc.ajouteSommet(arc.getArrivee());
			}
		}
		return XSucc;
	}
	@Override
	public InterfaceEnsembleSommet<S> listPred(InterfaceSommet<S> sommet)
	{
		InterfaceEnsembleSommet<S> XPred=Factory.ensembleSommet();
		for (A arc : ensemble)
		{
			if (arc.getArrivee().equals(sommet))
			{
				XPred.ajouteSommet(arc.getDepart());
			}
		}
		return XPred;
	}
	//Ajouts et suppressions d'éléments ----------------------------------------
	@Deprecated
	@Override
	public void supprElement(A arc)
	{
		supprArc(arc);
	}
	
	@Deprecated
	@Override
	public void ajoutElement(A arc)
	{
		ajouteArc(arc);
	}
	
	
	@Override
	public void ajouteArc(A arc)//Redéfinie pour chaque enfant (entre autre afin de faire des new)
	{
		if(!existeArc(arc))
		{
			ensemble.add(arc);
		}
	}
	@Override
	public void supprArc(A arcS)
	{
		for(A arcE : ensemble)
		{
			if(arcE.memeArc(arcS))
			{
				ensemble.remove(arcE);
				break;
			}
		}
	}
}