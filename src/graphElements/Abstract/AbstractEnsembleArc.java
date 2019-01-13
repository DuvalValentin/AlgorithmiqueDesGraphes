package graphElements.Abstract;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
import graphElements.Interfaces.InterfaceAbstractEnsemble;
import graphElements.Interfaces.InterfaceAbstractEnsembleArc;

public abstract class AbstractEnsembleArc<S,A extends Arc<S>> extends AbstractEnsemble<A> implements InterfaceAbstractEnsembleArc<S,A>,InterfaceAbstractEnsemble<A>
{
	//Constructeurs
	public AbstractEnsembleArc()
	{
		super();
	}
	//Test d'existence d'éléments
	@Override
	public boolean existeArc(A arc)
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
		for(A Arc : ensemble)
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
	//Séléction d'éléments
	@Override
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		EnsembleSommet<S> XSucc=new EnsembleSommet<S>();
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
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		EnsembleSommet<S> XPred=new EnsembleSommet<S>();
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
	@Override
	public void ajouteArc(A arc)//Redéfinie pour chaque enfant (entre autre afin de faire des new)
	{
		if(!this.existeArc(arc))
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
	@Override
	public void union(AbstractEnsemble<A> Ensemble)
	{
		for(A arc : Ensemble.ensemble)
		{
			ajouteArc(arc);
		}
	}
	
	/*public static AbstractEnsembleArc<S,A> union(AbstractEnsembleArc<S,A> Ensemble1,AbstractEnsembleArc<S,A> Ensemble2)
	{
	}*/
	@Override
	public void intersection(AbstractEnsemble<A> Ensemble)
	{
		for(A arc : ensemble)
		{
			if(!Ensemble.getEnsemble().contains(arc))//TODO on veut faire un existeArc ici, Intersection marchera mal pour arc valué
			{
				supprArc(arc);//TODO on ne peut pas faire ça
			}
		}
	}
}