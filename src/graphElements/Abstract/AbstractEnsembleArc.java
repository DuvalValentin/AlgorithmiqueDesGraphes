package graphElements.Abstract;

import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArc;

public abstract class AbstractEnsembleArc<S,A extends InterfaceArc<S>> extends AbstractEnsemble<A> implements InterfaceEnsembleArc<S,A>
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
	/*
	@Override
	public AbstractEnsemble<A> union(AbstractEnsemble<A> Ensemble)
	{
		AbstractEnsemble<A> union= new EnsembleArcNonValue<A>(this);
		for(A arc : Ensemble.ensemble)
		{
			ajouteArc(arc);
		}
	}*/
	
	/*public static <S,A extends InterfaceArc<S>> AbstractEnsembleArc<S,A> union(AbstractEnsembleArc<S,A> Ensemble1,AbstractEnsembleArc<S,A> Ensemble2) throws CloneNotSupportedException
	{
		@SuppressWarnings("unchecked")
		AbstractEnsembleArc<S,A> union=(AbstractEnsembleArc<S,A>) Ensemble1.clone();
		for(A arc : Ensemble2.getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}*/
	/*@Override
	public void intersection(AbstractEnsemble<A> Ensemble)
	{
		//faire un new arc pour régler le problème puis mettre ça dans la factory
		for(A arc : ensemble)
		{
			if(!Ensemble.getEnsemble().contains(arc))//TODO on veut faire un existeArc ici, Intersection marchera mal pour arc valué
			{
				supprArc(arc);//TODO on ne peut pas faire ça
			}
		}
	}*/
}