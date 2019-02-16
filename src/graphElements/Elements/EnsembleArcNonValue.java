package graphElements.Elements;

import java.util.HashSet;

import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArcNonValue;

public class EnsembleArcNonValue<S> extends AbstractEnsembleArc<S,InterfaceArc<S>> implements InterfaceEnsembleArcNonValue<S>
{
	//Constructeur
	public EnsembleArcNonValue(EnsembleArcNonValue<S> Ensemble)
	{
		for (InterfaceArc<S> arc : Ensemble.ensemble)
		{
			ajouteArc(arc);
		}
	}
	public EnsembleArcNonValue()
	{
		super();
	}
	//Getter
	@Override
	public HashSet<InterfaceArc<S>> getEnsemble()
	{
		return new HashSet<InterfaceArc<S>>(ensemble);
	}
	//Ajout et suppression d'éléments
	@Override
	public void ajouteArc(InterfaceArc<S> arc)
	{
		super.ajouteArc(new Arc<S>((Arc<S>) arc)); //Le new permet de créer une nouvelle instance
	}
	
	@Override
	public void ajouteArc(Sommet<S> depart,Sommet<S>arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}
	@Override
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		supprArc(new Arc<S>(depart,arrivee));
	}
	
	
	public static <S> EnsembleArcNonValue<S> union(EnsembleArcNonValue<S> Ensemble1,EnsembleArcNonValue<S> Ensemble2)
	{
		EnsembleArcNonValue<S> union = new EnsembleArcNonValue<S>(Ensemble1);
		for(InterfaceArc<S> arc : Ensemble2.getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}
	
	public static <S> EnsembleArcNonValue<S> intersection (EnsembleArcNonValue<S> Ensemble1,EnsembleArcNonValue<S> Ensemble2)
	{
		EnsembleArcNonValue<S> intersection = new EnsembleArcNonValue<S>(Ensemble1);
		for(InterfaceArc<S> arc : Ensemble1.getEnsemble())
		{
			if(!Ensemble2.existeArc(arc))
			{
				intersection.supprArc(arc);
			}
		}
		return intersection;
	}
	
	
}