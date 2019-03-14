package graphElements.Elements;

import java.util.HashSet;

import factory.Factory;
import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArcNonValue;
import graphElements.Interfaces.InterfaceSommet;

public class EnsembleArcNonValue<S> extends AbstractEnsembleArc<S,InterfaceArc<S>> implements InterfaceEnsembleArcNonValue<S>
{
	//Constructeur
	public EnsembleArcNonValue(InterfaceEnsembleArcNonValue<S> Ensemble)
	{
		for (InterfaceArc<S> arc : Ensemble.getEnsemble())
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
		super.ajouteArc(Factory.arcNonValue(arc));
	}
	
	@Override
	public void ajouteArc(InterfaceSommet<S> depart,InterfaceSommet<S>arrivee)
	{
		ajouteArc(Factory.arcNonValue(depart, arrivee));
	}
	@Override
	public void supprArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		supprArc(Factory.arcNonValue(depart, arrivee));
	}
	
	
	public static <S> InterfaceEnsembleArcNonValue<S> union(InterfaceEnsembleArcNonValue<S> Ensemble1,InterfaceEnsembleArcNonValue<S> Ensemble2)
	{
		InterfaceEnsembleArcNonValue<S> union = Factory.ensembleArcNonValue(Ensemble1);
		for(InterfaceArc<S> arc : Ensemble2.getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}
	
	public static <S> InterfaceEnsembleArcNonValue<S> intersection (InterfaceEnsembleArcNonValue<S> Ensemble1,InterfaceEnsembleArcNonValue<S> Ensemble2)
	{
		InterfaceEnsembleArcNonValue<S> intersection = Factory.ensembleArcNonValue(Ensemble1);
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